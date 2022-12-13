package com.github.sanaderer.POC.controllers.requests;

import com.github.sanaderer.POC.entities.AddressEntity;
import com.github.sanaderer.POC.entities.UserEntity;
import com.github.sanaderer.POC.enums.UserEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @Email
    private String email;

    @Pattern(regexp = "\\d{10,11}")
    private String telephone;

    //@Pattern(regexp = "^(((\\\\d{3}).(\\\\d{3}).(\\\\d{3})-(\\\\d{2}))?((\\\\d{2}).(\\\\d{3}).(\\\\d{3})/(\\\\d{4})-(\\\\d{2}))?)*$\", message = \"CPF: 000.000.000-00 / CNPJ: 00.000.000/0000-00")
    private String document;

    private UserEnum documentType;

    private LocalDateTime dateCreation;

    private LocalDateTime dateUpdate;

}
