package com.github.sanaderer.POC.services;

import com.github.sanaderer.POC.controllers.requests.UserRequest;
import com.github.sanaderer.POC.entities.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UserService {

    List<UserEntity> findAll();

    UserEntity findById(UUID id);

    UserEntity save(UserRequest userRequest);

    void deleteById(UUID id);

    UserEntity update(UUID id, UserRequest userRequest);
}
