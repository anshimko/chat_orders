package io.github.maxneutrino.webfluxwebsockets.service;

import io.github.maxneutrino.webfluxwebsockets.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class EventGenerator {

    private AtomicInteger counter = new AtomicInteger(0);

    private EventUnicastService eventUnicastService;

    private int count;

    @Autowired
    public EventGenerator(EventUnicastService eventUnicastService) {
        this.eventUnicastService = eventUnicastService;
    }

    @Scheduled(fixedDelay = 3000)
    public void generateEvent() {
        count++;
        Event event = new Event("event", count);
        eventUnicastService.onNext(String.valueOf(count));
    }
}
