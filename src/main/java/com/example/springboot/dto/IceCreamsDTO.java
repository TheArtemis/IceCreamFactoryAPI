package com.example.springboot.dto;

import java.util.List;

import com.example.springboot.model.IceCream;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IceCreamsDTO extends BaseDTO{
    List<IceCream> icecreams;

    public IceCreamsDTO(int code, String message, List<IceCream> icecreams){
        super(code, message);
        this.icecreams = icecreams;
    }
}
