package com.example.springboot.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cone {
    @Id
    private long id;

    private String name;
    private Integer price;

}
