package io.github.maxneutrino.webfluxwebsockets.controller;

import io.github.maxneutrino.webfluxwebsockets.web.WebSocketMessageSubscriber;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author Andrei Shymko
 * @since 10.02.2021
 */

@RestController
@RequestMapping("/save")
public class EventController {

    private final WebSocketMessageSubscriber defaultWebSocketHandler;

    public EventController(WebSocketMessageSubscriber defaultWebSocketHandler) {
        this.defaultWebSocketHandler = defaultWebSocketHandler;
    }

    @GetMapping
    public ResponseEntity<String> save(@RequestParam(name = "event") String event) {

        defaultWebSocketHandler.onNext(event);
        return ok(event);
    }
}