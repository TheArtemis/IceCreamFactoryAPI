package com.example.springboot.dto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class IceCreamRequestDTO {
    private long id;
    private String name;
    private String description;
    private Long cone;
    private List<Long> flavors;
    private List<Long> toppings;    

    public IceCreamRequestDTO(IceCreamRequestDTO iceCreamRequestDTO) {
        this.id = iceCreamRequestDTO.getId();
        this.name = iceCreamRequestDTO.getName();
        this.description = iceCreamRequestDTO.getDescription();
        this.cone = iceCreamRequestDTO.getCone();
        this.flavors = iceCreamRequestDTO.getFlavors();
        this.toppings = iceCreamRequestDTO.getToppings();        
    }

    public IceCreamRequestDTO(long id, String name, String description, Long cone, List<Long> flavors, List<Long> toppings) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cone = cone;
        this.flavors = flavors;
        this.toppings = toppings;       
    }
}