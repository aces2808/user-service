package com.sudocode.arrow.infrastructure.port;

import com.sudocode.arrow.core.model.User;
import com.sudocode.arrow.core.usecase.UserCreate;
import com.sudocode.arrow.core.usecase.UserRetrieve;
import com.sudocode.arrow.infrastructure.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Slf4j
public class UserPort implements UserCreate {

    private final UserRepository userRepository;

    public UserPort(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public <U extends Mono<User>> Mono<ServerResponse> save(U u) {
        return u.flatMap(user -> {
            log.info("Save User Data :: {}", user.toString());
            String sayHello = "Hello " + user.getFirstName() + " " + user.getLastName();
            User mongoUser = userRepository.save(user);
            log.info("Welcome to MCP :: {}", mongoUser.getId());
            return ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(sayHello), String.class);
        });
    }

}
