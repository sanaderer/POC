package com.github.sanaderer.POC.services.impl;

import com.github.sanaderer.POC.configuration.UserMapper;
import com.github.sanaderer.POC.controllers.requests.UserRequest;
import com.github.sanaderer.POC.entities.UserEntity;
import com.github.sanaderer.POC.repositories.UserRepository;
import com.github.sanaderer.POC.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public UserEntity save(UserRequest object) {
        UserEntity entity = userMapper.toEntity(object);
        return userRepository.save(entity);
    }
    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findById(UUID id) {
        return userRepository.findById(id);
    }
    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserEntity updateById(UUID id, UserRequest object) {
        UserEntity entity = userRepository.findById(id).orElseThrow(RuntimeException::new);
        return userRepository.save(userMapper.toUpdateEntity(object, entity));
    }

}
