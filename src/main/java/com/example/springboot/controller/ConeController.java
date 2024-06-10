package com.example.springboot.controller;
import com.example.springboot.dto.ConeDTO;
import com.example.springboot.dto.ConesDTO;
import com.example.springboot.model.Cone;
import com.example.springboot.repository.ConeRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/cone")
@CrossOrigin(origins = "http://127.0.0.1:5173")
@Tag(name = "Cone")
public class ConeController {
    @Autowired
    private ConeRepository coneRepository;

    @Deprecated
    @GetMapping("/")
    public String index() {
        return "This is the cone page";
    }

    @GetMapping("/{coneId}")
    public ConeDTO getCone(@PathVariable Long coneId) {
        Optional<Cone> cone = coneRepository.findById(coneId);

        if (!cone.isPresent()) {
            return new ConeDTO(-1, "Cone does not exist", null);
        }

        return new ConeDTO(1, "Cone exists", cone.get());
    }

    @GetMapping("/expensiveCones")
    public ConesDTO getExpensiveCones() {
        List<Cone> coneList = coneRepository.findExpensiveCones();
        return new ConesDTO(1, "Expensive cones", coneList);
    }

    @PostMapping("/add")
    public ConeDTO addCone(@RequestBody Cone cone) {
        boolean coneExists = coneRepository.existsById(cone.getId());

        if (cone.getName() == null || cone.getName().isEmpty() || cone.getPrice() == null)
            return new ConeDTO(-1, "Cone fields must be not null", null);

        if (!coneExists) {
            coneRepository.save(cone);
            return new ConeDTO(1, "Cone was added", cone);
        }
        return new ConeDTO(-1, "Cone already exists", cone);
    }

    @PutMapping("/update")
    public ConeDTO updateCone(@RequestBody Cone cone) {

        Optional<Cone> oldCone = coneRepository.findById(cone.getId());

        if (oldCone.isEmpty()) {
            return new ConeDTO(-1, "Cone does not exist", null);
        }

        /* Can use reflections but they are quite slow ??*/

        Cone newCone = oldCone.get();

        if (!(cone.getName() == null || cone.getName().isEmpty()))
            newCone.setName(cone.getName());

        if (!(cone.getPrice() == null))
            newCone.setPrice(cone.getPrice());

        coneRepository.save(newCone);
        return new ConeDTO(1, "Cone was updated", newCone);
    }
    @Transactional
    @DeleteMapping("/delete")
    public ConeDTO deleteCone(@RequestBody long coneId) {
        Optional<Cone> removedCone = coneRepository.findById(coneId);
        boolean coneExists = removedCone.isPresent();

        if (!coneExists) {
            return new ConeDTO(-1, "Cone does not exist", null);
        }

        coneRepository.deleteById(coneId);
        return new ConeDTO(1, "Cone was deleted", removedCone.get());
    }
}
