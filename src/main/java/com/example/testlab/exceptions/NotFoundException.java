package com.example.testlab.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NotFoundException extends Error {
    private Object entityId;
    private String entityName;
}

