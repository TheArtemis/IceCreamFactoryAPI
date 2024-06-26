package com.example.springboot.controller;
import com.example.springboot.dto.ToppingDTO;
import com.example.springboot.dto.ToppingsDTO;
import com.example.springboot.model.Topping;
import com.example.springboot.repository.ToppingRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5173")
@RequestMapping("/topping")
@Tag(name="Topping")
public class ToppingController {

    @Autowired
    private ToppingRepository toppingRepository;

    @GetMapping("/{toppingId}")
    public ToppingDTO getTopping(@PathVariable Long toppingId) {

        Optional<Topping> topping = toppingRepository.findById(toppingId);

        boolean toppingExists = topping.isPresent();

        if(!toppingExists)
            return new ToppingDTO(-1, "Topping does not exist", null);

        toppingRepository.save(topping.get());
        return new ToppingDTO(1, "Topping found", topping.get());
    }

    @GetMapping("/all")
    public ToppingsDTO getAllToppings(){
        List<Topping> toppings = toppingRepository.findAll();
        if (toppings.isEmpty())
            return new ToppingsDTO(-1, "There are no toppings!", toppings);

        String message = "I found " + toppings.size() + " toppings";

        return new ToppingsDTO(1, message, toppings);
    }

    @PostMapping("/add")
    public ToppingDTO add(@RequestBody Topping topping){
        if (topping.getName() == null || topping.getName().isEmpty() || topping.getPrice() == null)
            return new ToppingDTO(-1, "Topping fields must be not null", topping);

        boolean toppingExists = toppingRepository.existsById(topping.getId());

        if (toppingExists) {
            return new ToppingDTO(-1, "Topping already exists", null);
        }

        toppingRepository.save(topping);
        return new ToppingDTO(1, "Topping added with success", topping);
    }

    @PutMapping("/update")
    public ToppingDTO update(@RequestBody Topping topping){

        Optional<Topping> oldTopping = toppingRepository.findById(topping.getId());

        boolean oldToppingExists = oldTopping.isPresent();

        if (!oldToppingExists)
            return new ToppingDTO(-1, "Topping does not exist", null);

        Topping newTopping = oldTopping.get();

        if (topping.getName() != null && !topping.getName().isEmpty())
            newTopping.setName(topping.getName());

        if (topping.getPrice() != null)
            newTopping.setPrice(topping.getPrice());

        toppingRepository.save(newTopping);
        return new ToppingDTO(1, "Topping updated", newTopping);
    }

    @Transactional
    @DeleteMapping("/delete")
    public ToppingDTO delete(@RequestBody long toppingId) {
        Optional<Topping> removedTopping = toppingRepository.findById(toppingId);
        boolean toppingExists = removedTopping.isPresent();

        if (!toppingExists)
            return new ToppingDTO(-1, "Topping does not exist", null);

        toppingRepository.deleteById(toppingId);
        return new ToppingDTO(1, "Topping deleted", removedTopping.get());
    }
}


