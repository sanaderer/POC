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
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService, CepService {

    private final AddressRepository addressRepository;

    private final UserService userService;

    private final AddressMapper mapper;

    private final CepService cepService;


    public AddressEntity findById(UUID id) {
        return null;
    }

    public AddressEntity save(AddressRequest object, String cep, AddressEntity addressEntity) {
        AddressResponse addressResponse = cepService.getCep(cep);
        UserEntity user = userService.findById(object.getUserId());
        limitAddressesValidation(addressEntity);
        AddressEntity entity = mapper.toEntity(addressResponse, object, user);
        return addressRepository.save(entity);
    }

    private void limitAddressesValidation(AddressEntity address) {
        List<AddressEntity> list = new ArrayList<>();
        if(list.size() >5){
            list.remove(list.size() - 1);
        }

//        Iterator<AddressEntity> it = list.iterator();
//        while (it.hasNext()) {
//            if (address.equals(it.hasNext())){
//                it.remove();
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
