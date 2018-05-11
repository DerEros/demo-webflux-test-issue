package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

@Configuration
public class ReverserConfig {
    @Bean
    public RouterFunction reverserRouterFunction(@Autowired ReverserRoutes router) {
        return router.createRouterFunction();
    }
}
