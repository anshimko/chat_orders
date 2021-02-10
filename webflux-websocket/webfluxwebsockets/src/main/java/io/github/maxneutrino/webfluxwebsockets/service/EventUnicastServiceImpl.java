package io.github.maxneutrino.webfluxwebsockets.service;

import io.github.maxneutrino.webfluxwebsockets.model.Event;
import org.springframework.stereotype.Service;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

@Service
public class EventUnicastServiceImpl implements EventUnicastService {

    private EmitterProcessor<String> processor = EmitterProcessor.create();

    @Override
    public void onNext(String next) {
        processor.onNext(next);
    }

    @Override
    public Flux<String> getMessages() {
        return processor.publish().autoConnect();
    }
}
