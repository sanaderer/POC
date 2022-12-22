package com.github.sanaderer.POC.services;

import com.github.sanaderer.POC.controllers.requests.AddressRequest;
import com.github.sanaderer.POC.entities.AddressEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface AddressService {

    AddressEntity save(AddressRequest addressRequest, String cep);

    AddressEntity findById(UUID id);

    AddressEntity updateById(UUID id, AddressRequest addressRequest);

    void deleteById(UUID id);

}
