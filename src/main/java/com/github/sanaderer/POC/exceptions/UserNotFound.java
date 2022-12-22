package com.github.sanaderer.POC.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserNotFound extends RuntimeException {

    private String message;
    private String errorCode;
}
