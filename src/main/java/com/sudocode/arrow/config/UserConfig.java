package com.sudocode.arrow.config;

import com.sudocode.arrow.core.usecase.UserCreate;
import com.sudocode.arrow.infrastructure.port.UserPort;
import com.sudocode.arrow.infrastructure.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    private final UserRepository userRepository;

    public UserConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public UserPort userPort(UserRepository userRepository) {
        return new UserPort(userRepository);
    }
}
