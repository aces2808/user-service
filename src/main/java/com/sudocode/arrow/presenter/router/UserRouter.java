package com.sudocode.arrow.presenter.router;

import com.sudocode.arrow.presenter.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
public class UserRouter {


    @Bean
    public RouterFunction<ServerResponse> users(UserHandler userHandler) {
        return route(POST("/user").and(accept(MediaType.APPLICATION_JSON)), userHandler::save);
    }
}
