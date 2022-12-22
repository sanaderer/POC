package com.github.sanaderer.POC.services.impl;

import com.github.sanaderer.POC.controllers.mappers.UserMapper;
import com.github.sanaderer.POC.controllers.requests.UserRequest;
import com.github.sanaderer.POC.entities.AddressEntity;
import com.github.sanaderer.POC.entities.UserEntity;
import com.github.sanaderer.POC.enums.Errors;
import com.github.sanaderer.POC.enums.UserEnum;
import com.github.sanaderer.POC.exceptions.NotFoundException;
import com.github.sanaderer.POC.repositories.UserRepository;
import com.github.sanaderer.POC.services.UserService;
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

    public UserEntity save(UserRequest userRequest) {
        UserEntity entity = userMapper.toEntity(userRequest);
        return userRepository.save(entity);
    }

    public UserEntity findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() ->
                new NotFoundException(Errors.PC101.getMessage(),
                        Errors.PC101.getCode()));
    }

    public Page<UserEntity> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public List<AddressEntity> findAddressByUserId(UUID id) {
        var user = findById(id);
        try {
            return user.getAddresses();
        } catch (Exception exception) {
            throw new NotFoundException(Errors.PC101.getMessage(), Errors.PC101.getCode());
        }
    }

    public Page<UserEntity> findByDocumentType(UserEnum userEnum, Pageable pageable) {
        return userRepository.findByDocumentType(userEnum, pageable);
    }

    public UserEntity updateById(UUID id, UserRequest userRequest) {
        UserEntity entity = userRepository.findById(id).orElseThrow(() -> new
                NotFoundException(Errors.PC101.getMessage(),
                Errors.PC101.getCode()));
        return userRepository.save(userMapper.toUpdateEntity(userRequest, entity));
    }

    public void deleteById(UUID id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception exception) {
            throw new NotFoundException(Errors.PC101.getMessage(), Errors.PC101.getCode());
        }
    }

}
