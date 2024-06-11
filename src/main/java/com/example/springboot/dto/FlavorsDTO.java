package com.example.springboot.dto;
import com.example.springboot.model.Flavor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class FlavorsDTO extends BaseDTO{
    List<Flavor> flavors;

    public FlavorsDTO(int code, String message, List<Flavor> flavors) {
        super(code, message);
        this.flavors = flavors;
    }
}
