package com.github.sanaderer.POC.service.impl;

import com.github.sanaderer.POC.controller.mapper.UserMapper;
import com.github.sanaderer.POC.controller.requests.UserRequest;
import com.github.sanaderer.POC.entity.UserEntity;
import com.github.sanaderer.POC.enums.UserEnum;
import com.github.sanaderer.POC.repository.UserRepository;
import com.github.sanaderer.POC.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private final UserMapper userMapper;

    public UserEntity save(UserRequest object) {
        UserEntity entity = userMapper.toEntity(object);
        return userRepository.save(entity);
    }

    public Page<UserEntity> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Page<UserEntity> findByDocumentType(UserEnum userEnum, Pageable pageable){
        return userRepository.findByDocumentType(userEnum, pageable);
    }

    public UserEntity findById(UUID id) {
        return userRepository.getReferenceById(id);
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    public UserEntity updateById(UUID id, UserRequest object) {
        UserEntity entity = userRepository.findById(id).orElseThrow(RuntimeException::new);
        return userRepository.save(userMapper.toUpdateEntity(object, entity));
    }

}
