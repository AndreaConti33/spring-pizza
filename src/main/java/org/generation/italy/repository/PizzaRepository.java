package org.generation.italy.repository;

import java.util.List;

import org.generation.italy.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    List<Pizza> findByNomeContainingIgnoreCaseOrderByNome(String keyword);
}
