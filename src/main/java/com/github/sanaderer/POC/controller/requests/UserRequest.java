package com.github.sanaderer.POC.controller.requests;

import com.github.sanaderer.POC.enums.UserEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank
    @Email(message = "Please enter a valid email")
    @Length(max=100, message = "Email must be a maximum of 100 characters")
    private String email;

    @NotNull
    @Pattern(regexp = "\\d{10,11}")
    private String telephone;

    @NotBlank
    @Pattern(regexp = "(^\\d{3}.\\d{3}.\\d{3}-\\d{2}$)|(^\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}$)",
            message = "Enter CPF/CNPJ with complete format")
    private String document;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserEnum documentType;

    private List<AddressRequest> addresses = new ArrayList<>();

}
