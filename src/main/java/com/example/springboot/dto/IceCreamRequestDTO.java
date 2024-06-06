package com.example.springboot.dto;

import com.example.springboot.model.IceCream;
import com.example.springboot.repository.ConeRepository;
import com.example.springboot.repository.FlavorRepository;
import com.example.springboot.repository.IceCreamRepository;
import com.example.springboot.repository.ToppingRepository;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class IceCreamRequestDTO {
    private long id;
    private String name;

    private Long cone;

    private List<Long> flavors;
    private List<Long> toppings;

    public IceCreamRequestDTO(IceCreamRequestDTO iceCreamRequestDTO) {
        this.id = iceCreamRequestDTO.getId();
        this.name = iceCreamRequestDTO.getName();
        this.cone = iceCreamRequestDTO.getCone();
        this.flavors = iceCreamRequestDTO.getFlavors();
        this.toppings = iceCreamRequestDTO.getToppings();
    }

    public IceCreamRequestDTO(long id, String name, Long cone, List<Long> flavors, List<Long> toppings) {
        this.id = id;
        this.name = name;
        this.cone = cone;
        this.flavors = flavors;
        this.toppings = toppings;
    }
}