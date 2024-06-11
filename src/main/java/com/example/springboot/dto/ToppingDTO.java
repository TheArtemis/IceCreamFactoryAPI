package com.example.springboot.dto;

import com.example.springboot.model.Topping;

import java.io.Serializable;


public class ToppingDTO extends BaseDTO implements Serializable {
    private Topping topping;
    public ToppingDTO(int code, String message, Topping topping) {
        super(code, message);
        this.topping = topping;
    }

    public Topping getTopping() {
        return topping;
    }

    public void setTopping(Topping topping) {
        this.topping = topping;
    }

    @Override
    public String toString() {
        return "ToppingDTO{" +
                "topping=" + topping +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}


