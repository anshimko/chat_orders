package io.github.maxneutrino.webfluxwebsockets.web;

import org.springframework.stereotype.Component;
import reactor.core.publisher.UnicastProcessor;

import java.util.Optional;

/**
 * @author Andrei Shymko
 * @since 10.02.2021
 */

@Component
public class WebSocketMessageSubscriber {
    private final UnicastProcessor<String> eventPublisher;
    private Optional<String> lastReceivedEvent = Optional.empty();

    public WebSocketMessageSubscriber(UnicastProcessor<String> eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void onNext(String event) {
        lastReceivedEvent = Optional.of(event);
        eventPublisher.onNext(event);
    }

    public void onError(Throwable error) {
        //TODO log error
        error.printStackTrace();
    }

    public void onComplete() {

        lastReceivedEvent.ifPresent(event -> eventPublisher.onNext("Finish"));
    }
}