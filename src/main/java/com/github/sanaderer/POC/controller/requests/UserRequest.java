package com.github.sanaderer.POC.controller.requests;

import com.github.sanaderer.POC.enums.UserEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank
    @Email(message = "Invalid email")
    @Length(max=100, message = "Email must be a maximum of 100 characters")
    private String email;

    @NotNull
    @Pattern(regexp = "\\d{10,11}")
    private String telephone;

    @NotBlank
    //@Pattern(regexp = "^(((\\\\d{3}).(\\\\d{3}).(\\\\d{3})-(\\\\d{2}))?((\\\\d{2}).(\\\\d{3}).(\\\\d{3})/(\\\\d{4})-(\\\\d{2}))?)*$\", message = \"CPF: 000.000.000-00 / CNPJ: 00.000.000/0000-00")
    private String document;

    @NotNull
    private UserEnum documentType;

}
