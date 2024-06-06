package com.example.springboot.dto;

import com.example.springboot.model.IceCream;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IceCreamDTO  extends BaseDTO{
    IceCream iceCream;

    public IceCreamDTO(int code, String message, IceCream iceCream) {
        super(code, message);
        this.iceCream = iceCream;
    }

}
