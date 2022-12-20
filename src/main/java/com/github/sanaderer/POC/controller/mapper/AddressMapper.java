package com.github.sanaderer.POC.controller.mapper;

import com.github.sanaderer.POC.controller.requests.AddressRequest;
import com.github.sanaderer.POC.controller.responses.AddressResponse;
import com.github.sanaderer.POC.entity.AddressEntity;
import com.github.sanaderer.POC.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class AddressMapper {

    public AddressEntity toEntity(AddressResponse addressResponse, AddressRequest addressRequest, UserEntity userEntity) {
        AddressEntity entity = new AddressEntity();
        entity.setCep(addressResponse.getCep());
        entity.setStreet(addressResponse.getStreet());
        entity.setComplement(addressResponse.getComplement());
        entity.setNumber(addressRequest.getNumber());
        entity.setObservation(addressRequest.getObservation());
        entity.setNeighborhood(addressResponse.getNeighborhood());
        entity.setCity(addressResponse.getCity());
        entity.setState(addressResponse.getState());
        entity.setUser(userEntity);
        return entity;
    }

    public static AddressResponse toAddressDto(AddressEntity entity) {
        AddressResponse response = new AddressResponse();
        response.setCep(entity.getCep());
        response.setStreet(entity.getStreet());
        response.setComplement(entity.getComplement());
        response.setNumber(entity.getNumber());
        response.setObservation(entity.getObservation());
        response.setNeighborhood(entity.getNeighborhood());
        response.setCity(entity.getCity());
        response.setState(entity.getState());
        response.setMainAddress(entity.getMainAddress());
        response.setDateCreated(entity.getDateCreated());
        response.setDateUpdated(entity.getDateUpdated());
        response.setId(entity.getId());
        response.setUserId(entity.getUser().getId());
        return response;
    }

    public static AddressEntity toUpdateAddress(AddressRequest addressUpdate, AddressEntity addressEntity){
        addressEntity.setNumber(addressUpdate.getNumber());
        addressEntity.setObservation(addressUpdate.getObservation());
        return addressEntity;
    }
}
