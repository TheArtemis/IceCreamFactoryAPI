package com.example.springboot.repository;

import com.example.springboot.model.Flavor;
import com.example.springboot.model.Topping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToppingRepository extends JpaRepository<Topping, Long> {

}
