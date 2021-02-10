package io.github.maxneutrino.webfluxwebsockets.service;

import io.github.maxneutrino.webfluxwebsockets.model.Event;
import reactor.core.publisher.Flux;

public interface EventUnicastService {

    void onNext(String next);

    Flux<String> getMessages();
}
