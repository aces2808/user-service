package com.sudocode.arrow.presenter.handler;

import com.sudocode.arrow.core.model.User;
import com.sudocode.arrow.core.usercase.UserCreate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Component
public class UserHandler {

    private final UserCreate userCreate;

    public UserHandler(UserCreate userCreate) {
        this.userCreate = userCreate;
    }

    public Mono<ServerResponse> save(ServerRequest serverRequest) {
        return userCreate.save(serverRequest.bodyToMono(User.class));
    }
}
