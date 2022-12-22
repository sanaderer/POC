package com.github.sanaderer.POC.service.impl;

import com.github.sanaderer.POC.controller.mapper.AddressMapper;
import com.github.sanaderer.POC.controller.requests.AddressRequest;
import com.github.sanaderer.POC.controller.responses.AddressResponse;
import com.github.sanaderer.POC.entity.AddressEntity;
import com.github.sanaderer.POC.entity.UserEntity;
import com.github.sanaderer.POC.enums.Errors;
import com.github.sanaderer.POC.exceptions.MainAddressException;
import com.github.sanaderer.POC.repository.AddressRepository;
import com.github.sanaderer.POC.service.AddressService;
import com.github.sanaderer.POC.service.CepService;
import com.github.sanaderer.POC.service.UserService;
import lombok.AllArgsConstructor;
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

    public AddressEntity save(AddressRequest addressRequest, String cep, AddressEntity addressEntity) {
        AddressResponse addressResponse = cepService.getCep(cep);
        UserEntity userEntity = userService.findById(addressRequest.getUserId());
        AddressEntity toEntity = mapper.toEntity(addressResponse, addressRequest, userEntity);
        limitAddressesValidation(userEntity, addressRequest);
        toEntity.setMainAddress(addressRequest.getIsMain());
        return addressRepository.save(toEntity);
    }

    private void limitAddressesValidation(UserEntity userEntity, AddressRequest addressRequest) {
        List<AddressEntity> list = addressRepository.findByUserOrderByDateCreated(userEntity);
        if (list.isEmpty()) {
            addressRequest.setIsMain(true);
        } else {
            if (list.size() >= 5) {
                int index = Boolean.TRUE.equals(list.get(0).getMainAddress()) ? 1 : 0;
                addressRepository.delete(list.get(index));
            }
        }
    }

    public void mainAddressesValidation(AddressEntity addressEntity, AddressRequest addressRequest) {
        List<AddressEntity> addressEntities = addressRepository.findByUserOrderByDateCreated(addressEntity.getUser());
        if (Boolean.TRUE.equals(addressRequest.getIsMain())) {
            addressEntities.forEach(address -> address.setMainAddress(false));
            addressRepository.saveAllAndFlush(addressEntities);
        } else if (Boolean.TRUE.equals(addressEntity.getMainAddress())) {
            throw new MainAddressException(Errors.PC204.getMessage(), Errors.PC204.getCode());
        }
    }

    public AddressResponse getCep(@PathVariable String cep) {
        return cepService.getCep(cep);
    }

    public void deleteById(UUID id) {
        if (Boolean.TRUE.equals(findById(id).getMainAddress())) {
            throw new MainAddressException(Errors.PC204.getMessage(), Errors.PC204.getCode());
        }
        addressRepository.deleteById(id);
    }

    public AddressEntity updateById(UUID id, AddressRequest object) {
        AddressEntity addressEntity = addressRepository.findById(id).orElseThrow(RuntimeException::new);
        mainAddressesValidation(addressEntity, object);
        return addressRepository.save(AddressMapper.toUpdateAddress(object, addressEntity));
    }

}
