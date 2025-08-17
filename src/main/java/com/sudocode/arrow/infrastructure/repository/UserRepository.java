package com.sudocode.arrow.infrastructure.repository;

import com.sudocode.arrow.core.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
