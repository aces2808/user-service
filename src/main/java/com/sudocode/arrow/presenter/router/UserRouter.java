package com.sudocode.arrow.presenter.router;

import com.sudocode.arrow.presenter.handler.UserHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Slf4j
@Component
public class UserRouter {


    @Bean
    public RouterFunction<ServerResponse> users(UserHandler userHandler) {
        return route(POST("/user").and(accept(MediaType.APPLICATION_JSON)), userHandler::save);
    }

    @Bean
    public RouterFunction<ServerResponse> user(UserHandler userHandler) {
        log.info("UserRouter :: user");
        return route(GET("/user/{id}").and(accept(MediaType.APPLICATION_JSON)), userHandler::get);
    }
}
