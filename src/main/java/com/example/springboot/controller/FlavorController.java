package com.example.springboot.controller;

import com.example.springboot.dto.FlavorDTO;
import com.example.springboot.model.Flavor;
import com.example.springboot.repository.ConeRepository;
import com.example.springboot.repository.FlavorRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/flavor")
@Tag(name="Flavor")
public class FlavorController {

    @Autowired
    private FlavorRepository flavorRepository;

    @Operation(
            description = "Deprecated index endpoint for Flavor",
            summary = "Returns a simple Flavors Page message",
            deprecated = true
    )
    @GetMapping("/")
    public String index() {
        return "Flavours Page!";
    }
    @Operation(
            description = "Get endpoint to retrieve a specific Flavor by ID",
            summary = "Retrieve a Flavor by its ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "1"
                    ),
                    @ApiResponse(
                            description = "Flavor does not exist",
                            responseCode = "-1"
                    )
            }
    )
    @GetMapping("/{flavorId}")
    public FlavorDTO getFlavor(@PathVariable long flavorId) {
        Optional<Flavor> flavor = flavorRepository.findById(flavorId);
        if (flavor.isEmpty()) {
            return new FlavorDTO(-1, "Flavor does not exist", null);
        }
        return new FlavorDTO(1, "Flavor exists", flavor.get());
    }

    @Operation(
            description = "Post endpoint to add a new Flavor",
            summary = "Add a new Flavor",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "1"
                    ),
                    @ApiResponse(
                            description = "Flavor fields must be not null",
                            responseCode = "-1"
                    ),
                    @ApiResponse(
                            description = "Flavor already exists",
                            responseCode = "-1"
                    )
            }
    )
    @PostMapping("/add")
    public FlavorDTO addFlavor(@RequestBody Flavor flavor) {
        if (flavor.getName() == null || flavor.getName().isEmpty())
            return new FlavorDTO(-1, "Flavor fields must be not null", null);

        boolean flavorExists = flavorRepository.existsById(flavor.getId());

        if (flavorExists) {
            return new FlavorDTO(-1, "Flavor already exists", null);
        }

        flavorRepository.save(flavor);
        return new FlavorDTO(1, "Flavor added with success", flavor);
    }

    @Operation(
            description = "Put endpoint to update an existing Flavor",
            summary = "Update an existing Flavor",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "1"
                    ),
                    @ApiResponse(
                            description = "Flavor does not exist",
                            responseCode = "-1"
                    )
            }
    )
    @PutMapping("/update")
    public FlavorDTO updateFlavor(@RequestBody Flavor flavor) {
        Optional<Flavor> oldFlavor = flavorRepository.findById(flavor.getId());
        boolean flavorExists = oldFlavor.isPresent();

        if (!flavorExists)
            return new FlavorDTO(-1, "Flavor does not exist", null);

        Flavor newFlavor = oldFlavor.get();

        if (flavor.getName() != null && !flavor.getName().isEmpty())
            newFlavor.setName(flavor.getName());

        flavorRepository.save(newFlavor);
        return new FlavorDTO(1, "Flavor replaced with success", newFlavor);
    }

    @Operation(
            description = "Delete endpoint to remove a Flavor by ID",
            summary = "Remove a Flavor by its ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "1"
                    ),
                    @ApiResponse(
                            description = "Flavor does not exist",
                            responseCode = "-1"
                    )
            }
    )
    @Transactional
    @DeleteMapping("/delete")
    public FlavorDTO deleteFlavor(@RequestBody long flavorId) {
        Optional<Flavor> removedFlavor = flavorRepository.findById(flavorId);
        boolean flavorExists = removedFlavor.isPresent();

        if (!flavorExists)
            return new FlavorDTO(-1, "Flavor does not exist", null);

        flavorRepository.deleteById(flavorId);
        return new FlavorDTO(1, "Flavor deleted", removedFlavor.get());
    }
}