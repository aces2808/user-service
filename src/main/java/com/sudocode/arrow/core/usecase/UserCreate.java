package com.sudocode.arrow.core.usecase;

import com.sudocode.arrow.core.model.User;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface UserCreate {

    <U extends Mono<User>> Mono<ServerResponse> save(U u);
}
