package com.example.springboot.repository;

import com.example.springboot.model.IceCream;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IceCreamRepository extends JpaRepository<IceCream, Long> {
public List<IceCream> findAllByOrderByNameAsc();
}
