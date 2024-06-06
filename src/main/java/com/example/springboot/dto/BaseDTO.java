package com.example.springboot.dto;

import lombok.*;

@Getter
@Setter
public class BaseDTO {
    int code;
    String message;

    public BaseDTO(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
