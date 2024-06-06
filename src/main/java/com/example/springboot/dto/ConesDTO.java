package com.example.springboot.dto;

import com.example.springboot.model.Cone;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ConesDTO extends BaseDTO{
    List<Cone> cones;

    public ConesDTO(int code, String message, List<Cone> cones) {
        super(code, message);
        this.cones = cones;
    }
}
