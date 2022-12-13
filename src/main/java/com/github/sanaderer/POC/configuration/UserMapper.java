package com.github.sanaderer.POC.configuration;

import com.github.sanaderer.POC.controllers.requests.UserRequest;
import com.github.sanaderer.POC.entities.UserEntity;
import org.springframework.context.annotation.Configuration;

@Configuration
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
}
