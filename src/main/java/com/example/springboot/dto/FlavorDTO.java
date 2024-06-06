package com.example.springboot.dto;

import com.example.springboot.model.Flavor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class FlavorDTO extends BaseDTO{
    private Flavor flavor;

    public FlavorDTO(int id, String description, Flavor flavor) {
        super(id, description);
        this.flavor = flavor;
    }
}
