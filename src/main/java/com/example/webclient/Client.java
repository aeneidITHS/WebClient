package com.example.webclient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
class Client implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        WebClient Client = WebClient.create("http://localhost:8080");
        Mono<Object> products =

    }

}
