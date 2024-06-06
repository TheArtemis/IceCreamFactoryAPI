package com.example.springboot.dto;


import com.example.springboot.model.Topping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class TempDTO {
    private int code;
    private String message;
    private Topping topping;
}
