package com.github.sanaderer.POC.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Errors {

    PC101("PC-101", "This user does not exist."),
    PC201("PC-201", "This address does not exist."),
    PC202("PC-204", "You must have at least one main address.");

    private final String code;
    private final String message;

}
