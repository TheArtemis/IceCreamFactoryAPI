package com.example.springboot.dto;
import com.example.springboot.model.Topping;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ToppingsDTO extends BaseDTO{
    List<Topping> toppings;

    public ToppingsDTO(int code, String message, List<Topping> toppings) {
        super(code, message);
        this.toppings = toppings;
    }
}
