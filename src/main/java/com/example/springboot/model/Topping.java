package com.example.springboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity
public class Topping {
    @Id
    private long id;
    private String name;
    private Integer price;
}
