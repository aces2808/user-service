package com.sudocode.arrow.presenter.handler;

import com.sudocode.arrow.core.model.User;
import com.sudocode.arrow.core.usecase.UserCreate;
import com.sudocode.arrow.infrastructure.port.UserPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Component
public class UserHandler {

    private final UserPort userPort;

    public UserHandler(UserPort userPort) {
        this.userPort = userPort;
    }

    public Mono<ServerResponse> save(ServerRequest serverRequest) {
        return userPort.save(serverRequest.bodyToMono(User.class));
    }

}
