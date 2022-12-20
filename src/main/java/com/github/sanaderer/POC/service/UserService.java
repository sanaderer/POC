package com.github.sanaderer.POC.service;

import com.github.sanaderer.POC.controller.requests.UserRequest;
import com.github.sanaderer.POC.entity.UserEntity;
import com.github.sanaderer.POC.enums.UserEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserService {

    Page<UserEntity> findAll(Pageable pageable);

    UserEntity findById(UUID id);

    Page<UserEntity> findByDocumentType(UserEnum userEnum, Pageable pageable);

    UserEntity save(UserRequest object);

    void deleteById(UUID id);

    UserEntity updateById(UUID id, UserRequest object);

}
