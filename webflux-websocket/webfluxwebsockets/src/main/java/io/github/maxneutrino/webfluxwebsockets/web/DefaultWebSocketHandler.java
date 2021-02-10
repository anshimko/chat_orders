package io.github.maxneutrino.webfluxwebsockets.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class DefaultWebSocketHandler implements WebSocketHandler {

    private final Flux<String> outputEvents;
    private final WebSocketMessageSubscriber subscriber;

    @Autowired
    public DefaultWebSocketHandler(Flux<String> events, WebSocketMessageSubscriber subscriber) {
        this.outputEvents = events;
        this.subscriber = subscriber;
    }

    /*
     * how do it  https://blog.monkey.codes/how-to-build-a-chat-app-using-webflux-websockets-react/
     */
    @Override
    public Mono<Void> handle(WebSocketSession session) {

        return session.receive()
                .map(WebSocketMessage::getPayloadAsText)
                .doOnNext(subscriber::onNext)
                .doOnError(subscriber::onError)
                .doOnComplete(subscriber::onComplete)
                .zipWith(session.send(outputEvents.map(session::textMessage)))
                .then();
    }

}