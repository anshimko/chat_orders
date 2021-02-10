package io.github.maxneutrino.webfluxwebsockets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

@SpringBootApplication
@EnableScheduling
public class WebfluxwebsocketsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxwebsocketsApplication.class, args);
    }

    @Bean
    public UnicastProcessor<String> eventPublisher() {
        return UnicastProcessor.create();
    }

    @Bean
    public Flux<String> events(UnicastProcessor<String> eventPublisher) {
        return eventPublisher
                .replay(10)
                .autoConnect();
    }
}