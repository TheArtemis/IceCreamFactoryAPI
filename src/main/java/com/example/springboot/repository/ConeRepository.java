package com.example.springboot.repository;

import com.example.springboot.model.Cone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConeRepository extends JpaRepository<Cone, Long> {
    public List<Cone> findConeByPrice(Integer price);

    @Query("SELECT c FROM Cone c WHERE c.price > 50 ORDER BY c.price")
    public List<Cone> findExpensiveCones();
}
