package org.generation.italy.service;

import java.util.List;

import org.generation.italy.model.Pizza;
import org.generation.italy.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository repository;

    public List<Pizza> findAllByName() {
        return repository.findAll(Sort.by("nome"));
    }

    public Pizza create(Pizza pizza) {
        return repository.save(pizza);
    }

    public Pizza getById(Integer id) {
        return repository.getById(id);
    }

    public Pizza update(Pizza pizza) {
        return repository.save(pizza);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public List<Pizza> findByKeywordSortedByName(String keyword) {
        return repository.findByNomeContainingIgnoreCaseOrderByNome(keyword);
    }


}
