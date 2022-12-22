package com.github.sanaderer.POC.services;

import com.github.sanaderer.POC.controllers.requests.UserRequest;
import com.github.sanaderer.POC.entities.UserEntity;
import com.github.sanaderer.POC.enums.UserEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserService {

    UserEntity save(UserRequest userRequest);

    UserEntity findById(UUID id);

    Page<UserEntity> findAll(Pageable pageable);

    Page<UserEntity> findByDocumentType(UserEnum userEnum, Pageable pageable);

    UserEntity updateById(UUID id, UserRequest userRequest);

    void deleteById(UUID id);

}
