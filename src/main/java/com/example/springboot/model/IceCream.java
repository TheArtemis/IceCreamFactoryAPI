package com.example.springboot.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "icecream")
public class IceCream {
    @Schema(name="Ice Cream Id", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "icecream_gen")
    @SequenceGenerator(name="icecream_gen", sequenceName = "icecream_seq", allocationSize = 1)
    private long id;

    @Schema(name="Ice Cream name", example = "Pistacchione")
    private String name;

    @Schema(name="Ice Cream description", example = "The best Ice Cream")
    private String description;

    @Schema(name="Cone", example = """
            {
                        "id": 2,
                        "name": "minion",
                        "price": 900
                    }""")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cone", nullable=false)
    private Cone cone;

    @Schema(name="Flavors", example= """
            {
                            "id": 1,
                            "name": "strawberry"
                        },
                        {
                            "id": 2,
                            "name": "banana"
                        }""")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "icecream_flavor")
    private Set<Flavor> flavors;

    @Schema(name="Toppings", example = "{\n" +
            "                \"id\": 1,\n" +
            "                \"name\": \"whipped cream\",\n" +
            "                \"price\": 100\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 3,\n" +
            "                \"name\": \"pistacchio\",\n" +
            "                \"price\": 300\n" +
            "            }")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "icecream_topping")
    private Set<Topping> toppings;    
}

//manytomany

//fetch type