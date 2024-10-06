package com.sudocode.arrow.infrastructure.port;

import com.sudocode.arrow.core.model.User;
import com.sudocode.arrow.core.usercase.UserCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Slf4j
public class UserPort implements UserCreate {

    @Override
    public <U extends Mono<User>> Mono<ServerResponse> save(U u) {
        return u.flatMap(user -> {
            log.info("User data:: {}", user.toString());
            String sayHello = "Hello " + user.getFirstName() + " " + user.getLastName();
            log.info("User sayHello:: {}", sayHello);
            return ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(sayHello), String.class);
        });
    }
}
