package com.github.sanaderer.POC.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {

    private UUID userId;

    @NotBlank
    private String number;

    private String observation;

}
