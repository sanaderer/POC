package com.github.sanaderer.POC.controller.mapper;

import com.github.sanaderer.POC.controller.requests.UserRequest;
import com.github.sanaderer.POC.controller.responses.UserResponse;
import com.github.sanaderer.POC.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class UserMapper {
    private static final String removeMask = "[^\\d ]";

    public UserEntity toEntity(UserRequest userRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setDocument(userRequest.getDocument().replaceAll(removeMask, ""));
        userEntity.setDocumentType(userRequest.getDocumentType());
        userEntity.setTelephone(Long.valueOf(userRequest.getTelephone().replaceAll(removeMask, "")));
        return userEntity;
    }

    public UserEntity toUpdateEntity(UserRequest userUpdate, UserEntity userEntity) {
        userEntity.setEmail(userUpdate.getEmail());
        userEntity.setDocument(userUpdate.getDocument().replaceAll(removeMask, ""));
        userEntity.setDocumentType(userUpdate.getDocumentType());
        userEntity.setTelephone(Long.valueOf(userUpdate.getTelephone().replaceAll(removeMask, "")));
        return userEntity;
    }

    public static UserResponse toDto(UserEntity userEntity) {
        UserResponse response = new UserResponse();
        response.setId(userEntity.getId());
        response.setEmail(userEntity.getEmail());
        response.setDocument(userEntity.getDocument());
        response.setDocumentType(userEntity.getDocumentType());
        response.setTelephone(userEntity.getTelephone());
        response.setDateCreated(userEntity.getDateCreated());
        response.setDateUpdated(userEntity.getDateUpdated());
        return response;
    }

}
