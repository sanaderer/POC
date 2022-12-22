package com.github.sanaderer.POC.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CepNotFoundException extends RuntimeException {

    private String message;
    private String errorCode;

}
