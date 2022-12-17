package com.github.sanaderer.POC.service;

import com.github.sanaderer.POC.controller.requests.AddressRequest;
import com.github.sanaderer.POC.entity.AddressEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface AddressService {

    AddressEntity findById(UUID id);

    AddressEntity save(AddressRequest object, String cep, AddressEntity addressEntity);

    void deleteById(UUID id);

    AddressEntity updateById(UUID id, AddressRequest object);

}
