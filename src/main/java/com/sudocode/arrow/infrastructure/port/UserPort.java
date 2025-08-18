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

//TODO Refactor this to use a service layer for business logic
@Slf4j
public record UserPort(UserRepository userRepository) implements UserCreate, UserRetrieve {


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

    @Override
    public Mono<ServerResponse> get(String id) {
        return Mono.just(id).flatMap(
                userId -> {
                    log.info("Get User Data :: {}", userRepository.findUserById(userId).toString());
                    return ok().contentType(MediaType.APPLICATION_JSON).body(Mono.justOrEmpty(userRepository.findUserById(userId)), User.class);
                }
        );
    }
}
