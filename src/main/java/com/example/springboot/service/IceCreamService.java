package com.example.springboot.service;

import com.example.springboot.dto.IceCreamRequestDTO;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.model.Cone;
import com.example.springboot.model.Flavor;
import com.example.springboot.model.IceCream;
import com.example.springboot.model.Topping;
import com.example.springboot.repository.ConeRepository;
import com.example.springboot.repository.FlavorRepository;
import com.example.springboot.repository.IceCreamRepository;
import com.example.springboot.repository.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IceCreamService {

    @Autowired
    private IceCreamRepository iceCreamRepository;

    @Autowired
    private FlavorRepository flavorRepository;

    @Autowired
    private ConeRepository coneRepository;

    @Autowired
    private ToppingRepository toppingRepository;

    public Optional<IceCream> findIceCreamById(Long id) {
        return iceCreamRepository.findById(id);
    }

    public boolean doesIceCreamExist(Long id) {
        return iceCreamRepository.existsById(id);
    }

    public List<Topping> findToppingsById(List<Long> ids) {
        return toppingRepository.findAllById(ids);
    }

    public List<Flavor> findFlavorsById(List<Long> ids) {
        return flavorRepository.findAllById(ids);
    }

    public Optional<Cone> findConeById(Long id) {
        return coneRepository.findById(id);
    }

    public void saveIceCream(IceCream iceCream) {
        iceCreamRepository.save(iceCream);
    }

    public void deleteIceCream(Long id) {
        iceCreamRepository.deleteById(id);
    }

    public IceCreamRequestDTO toIceCreamRequestDTO(IceCream iceCream) {
        long iceCreamId = iceCream.getId();
        String iceCreamName = iceCream.getName();
        String iceCreamDescription = iceCream.getDescription();
        List<Long> iceCreamFlavors = iceCream.getFlavors().stream().map(Flavor::getId).toList();
        List<Long> iceCreamToppings = iceCream.getToppings().stream().map(Topping::getId).toList();

        long coneId = iceCream.getCone().getId();
        
        return new IceCreamRequestDTO(iceCreamId, iceCreamName, iceCreamDescription, coneId, iceCreamFlavors, iceCreamToppings);
    }

    public IceCream toIceCream(IceCreamRequestDTO iceCreamRequestDTO) throws ResourceNotFoundException {
        List<Flavor> iceCreamFlavors = findFlavorsById(iceCreamRequestDTO.getFlavors());
        if ( iceCreamRequestDTO.getFlavors().isEmpty() || iceCreamFlavors.size() != iceCreamRequestDTO.getFlavors().size())
            throw new ResourceNotFoundException("Flavors not found. Flavors given: " + iceCreamRequestDTO.getFlavors() + "Flavors gotten: " + iceCreamFlavors);

        Set<Flavor> iceCreamFlavorsSet = new HashSet<>(iceCreamFlavors);

        List<Topping> iceCreamToppings = findToppingsById(iceCreamRequestDTO.getToppings());
        if (iceCreamRequestDTO.getToppings().isEmpty() || iceCreamToppings.size() != iceCreamRequestDTO.getToppings().size())
            throw new ResourceNotFoundException("Toppings not found");

        Set<Topping> iceCreamToppingsSet = new HashSet<>(iceCreamToppings);

        Optional<Cone> iceCreamCone = findConeById(iceCreamRequestDTO.getCone());
        if (iceCreamCone.isEmpty())
            throw new ResourceNotFoundException("Cone not found");

        String iceCreamName = iceCreamRequestDTO.getName();
        long iceCreamId = iceCreamRequestDTO.getId();
        String iceCreamDescription = iceCreamRequestDTO.getDescription();
        return new IceCream(iceCreamId, iceCreamName, iceCreamDescription, iceCreamCone.get(), iceCreamFlavorsSet, iceCreamToppingsSet);
    }

    // writes all the non-null fields of the first DTO with the second one.
    public IceCreamRequestDTO mergeIceCreamRequestDTO(IceCreamRequestDTO dto1, IceCreamRequestDTO dto2) {

        IceCreamRequestDTO iceCreamRequestDTO = new IceCreamRequestDTO(dto2);

        if (dto1.getName() != null && !dto1.getName().isEmpty())
            iceCreamRequestDTO.setName(dto1.getName());

        if (dto1.getCone() != null)
            iceCreamRequestDTO.setCone(dto1.getCone());

        if (dto1.getFlavors() != null)
            iceCreamRequestDTO.setFlavors(dto1.getFlavors());

        if (dto1.getToppings() != null)
            iceCreamRequestDTO.setToppings(dto1.getToppings());

        if (dto1.getDescription() != null && !dto1.getDescription().isEmpty())
            iceCreamRequestDTO.setDescription(dto1.getDescription());

        return iceCreamRequestDTO;
    }
}
