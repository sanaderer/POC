package com.github.sanaderer.POC.service;

import com.github.sanaderer.POC.controller.requests.UserRequest;
import com.github.sanaderer.POC.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UserService {

    List<UserEntity> findAll();

    UserEntity findById(UUID id);

    UserEntity save(UserRequest object);

    void deleteById(UUID id);

    UserEntity updateById(UUID id, UserRequest object);
}
