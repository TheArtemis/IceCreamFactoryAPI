package com.example.springboot.dto;

import com.example.springboot.model.Cone;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class ConeDTO extends BaseDTO{
    Cone cone;
    public ConeDTO(int code, String message, Cone cone) {
        super(code, message);
        this.cone = cone;
    }
}
