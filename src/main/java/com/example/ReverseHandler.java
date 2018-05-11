package com.example;

import com.example.model.ReverserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
public class ReverseHandler {

    @Autowired
    ReverserService reverserService;

    public Mono<ServerResponse> reverse(ServerRequest serverRequest) {
        ReverserResult reverseResult = reverserService.reverse(serverRequest.pathVariable("word"));

        return ServerResponse.ok().body(fromObject(reverseResult));
    }
}
