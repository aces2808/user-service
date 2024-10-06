package com.sudocode.arrow.config;

import com.sudocode.arrow.core.usercase.UserCreate;
import com.sudocode.arrow.infrastructure.port.UserPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public UserCreate createUser() {
        return new UserPort();
    }
}
