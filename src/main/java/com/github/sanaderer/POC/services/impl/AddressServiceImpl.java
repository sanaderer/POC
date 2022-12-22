package com.github.sanaderer.POC.services.impl;

import com.github.sanaderer.POC.controllers.mappers.AddressMapper;
import com.github.sanaderer.POC.controllers.requests.AddressRequest;
import com.github.sanaderer.POC.controllers.responses.AddressResponse;
import com.github.sanaderer.POC.entities.AddressEntity;
import com.github.sanaderer.POC.entities.UserEntity;
import com.github.sanaderer.POC.enums.Errors;
import com.github.sanaderer.POC.exceptions.MainAddressException;
import com.github.sanaderer.POC.exceptions.NotFoundException;
import com.github.sanaderer.POC.repositories.AddressRepository;
import com.github.sanaderer.POC.repositories.UserRepository;
import com.github.sanaderer.POC.services.AddressService;
import com.github.sanaderer.POC.services.CepService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressMapper mapper;
    private final CepService cepService;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public AddressEntity save(AddressRequest addressRequest, String cep) {
        AddressResponse addressResponse = cepService.getCep(cep);
        UserEntity userEntity = userRepository.findById(addressRequest.getUserId()).orElseThrow(() ->
                new NotFoundException(Errors.PC101.getMessage(),
                Errors.PC101.getCode()));
        AddressEntity toEntity = mapper.toEntity(addressResponse, addressRequest, userEntity);
        limitAddressesValidation(userEntity, addressRequest);
        toEntity.setMainAddress(addressRequest.getIsMain());
        return addressRepository.save(toEntity);
    }

    public AddressEntity findById(UUID id) {
        return addressRepository.getReferenceById(id);
    }

    public AddressEntity updateById(UUID id, AddressRequest addressRequest) {
        AddressEntity addressEntity = addressRepository.findById(id).orElseThrow(() ->
                new NotFoundException(Errors.PC201.getMessage(),
                        Errors.PC201.getCode()));
        mainAddressesValidation(addressEntity, addressRequest);
        return addressRepository.save(AddressMapper.toUpdateAddress(addressRequest, addressEntity));
    }

    public void deleteById(UUID id) {
        if (Boolean.TRUE.equals(findById(id).getMainAddress())) {
            throw new MainAddressException(Errors.PC202.getMessage(), Errors.PC202.getCode());
        }
        addressRepository.deleteById(id);
    }

    private void limitAddressesValidation(UserEntity userEntity, AddressRequest addressRequest) {
        List<AddressEntity> listAddresses = addressRepository.findByUserOrderByDateCreated(userEntity);
        if (listAddresses.isEmpty()) {
            addressRequest.setIsMain(true);
        } else {
            if (listAddresses.size() >= 5) {
                int index = Boolean.TRUE.equals(listAddresses.get(0).getMainAddress()) ? 1 : 0;
                addressRepository.delete(listAddresses.get(index));
            }
        }
    }

    public void mainAddressesValidation(AddressEntity addressEntity, AddressRequest addressRequest) {
        List<AddressEntity> addressEntities = addressRepository.findByUserOrderByDateCreated(addressEntity.getUser());
        if (Boolean.TRUE.equals(addressRequest.getIsMain())) {
            addressEntities.forEach(address -> address.setMainAddress(false));
            addressRepository.saveAllAndFlush(addressEntities);
        } else if (Boolean.TRUE.equals(addressEntity.getMainAddress())) {
            throw new MainAddressException(Errors.PC202.getMessage(), Errors.PC202.getCode());
        }
    }
}
