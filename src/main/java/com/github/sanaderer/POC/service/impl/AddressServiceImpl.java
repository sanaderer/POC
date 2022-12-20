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
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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
        return addressRepository.getReferenceById(id);
    }

    public AddressEntity save(AddressRequest object, String cep, AddressEntity addressEntity) {
        AddressResponse addressResponse = cepService.getCep(cep);
        UserEntity userEntity = userService.findById(object.getUserId());
        AddressEntity toEntity = mapper.toEntity(addressResponse, object, userEntity);
        limitAddressesValidation(userEntity);
        toEntity.setMainAddress(true);
        return addressRepository.save(toEntity);
    }

    private void limitAddressesValidation(UserEntity userEntity) {
        List<AddressEntity> list = addressRepository.findByUserOrderByDateCreated(userEntity);
        list.forEach(addressEntity -> addressEntity.setMainAddress(false));
        addressRepository.saveAllAndFlush(list);
        if(list.size() >= 5){
            addressRepository.delete(list.get(0));
        }
    }

    public AddressResponse getCep(@PathVariable String cep) {
        return cepService.getCep(cep);
    }


    public void deleteById(UUID id) {
        addressRepository.deleteById(id);
    }

    public AddressEntity updateById(UUID id, AddressRequest object) {
       AddressEntity addressEntity = addressRepository.findById(id).orElseThrow(RuntimeException::new);
       return addressRepository.save(AddressMapper.toUpdateAddress(object, addressEntity));
    }

}
