package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Component
public class ReverserRoutes {
    @Autowired
    ReverseHandler reverseHandler;

    public RouterFunction<ServerResponse> createRouterFunction() {
        return RouterFunctions.route(GET("/reverse/{word}"), reverseHandler::reverse);
    }
}
