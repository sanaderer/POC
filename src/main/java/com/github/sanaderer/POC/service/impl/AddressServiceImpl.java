package com.github.sanaderer.POC.service.impl;

import com.github.sanaderer.POC.controller.mapper.AddressMapper;
import com.github.sanaderer.POC.controller.requests.AddressRequest;
import com.github.sanaderer.POC.controller.responses.AddressResponse;
import com.github.sanaderer.POC.entity.AddressEntity;
import com.github.sanaderer.POC.entity.UserEntity;
import com.github.sanaderer.POC.repository.AddressRepository;
import com.github.sanaderer.POC.service.AddressService;
import com.github.sanaderer.POC.service.CepService;
import com.github.sanaderer.POC.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService, CepService{

    private final AddressRepository addressRepository;

    private final UserService userService;

    private final AddressMapper mapper;

    private final CepService cepService;


    public AddressEntity findById(UUID id) {
        return null;
    }

    public AddressEntity save(AddressRequest object, String cep) {
        AddressResponse addressResponse = cepService.getCep(cep);
        UserEntity user = userService.findById(object.getUserId());
        validateAddress(user);
        AddressEntity entity = mapper.toEntity(addressResponse, object, user);
        return addressRepository.save(entity);
    }
    private void validateAddress(final UserEntity entity){
        if(entity.getAddresses().size() >= 5){
            List<AddressEntity> list = new ArrayList<>();
            int indexOfLastAddress = (entity.getAddresses().size() - 1);
            addressRepository.deleteById(entity.getId());
            list.remove(indexOfLastAddress);
        }
    }

    public AddressResponse getCep(@PathVariable String cep) {
        return cepService.getCep(cep);
    }


    public void deleteById(UUID id) {
        addressRepository.deleteById(id);
    }

    public AddressEntity updateById(UUID id, AddressRequest object) {
        return null;
    }

}
