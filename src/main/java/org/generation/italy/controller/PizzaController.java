package org.generation.italy.controller;

import org.generation.italy.model.Pizza;
import org.generation.italy.service.IngredienteService;
import org.generation.italy.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pizze")

public class PizzaController {

    @Autowired
    private PizzaService service;

    @Autowired
    private IngredienteService service2;

    @GetMapping
    public String list(Model model, @RequestParam(name = "keyword", required = false) String keyword) {
        List<Pizza> result;
        if (keyword != null && !keyword.isEmpty()) {
            result = service.findByKeywordSortedByName(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            result = service.findAllByName();
        }
        model.addAttribute("list", result);
        return "list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredientiList", service2.findAllByNome());
        return "create";
    }

    @PostMapping("/create")
    public String doCreate(@ModelAttribute("pizza") Pizza pizza, Model model) {
        model.addAttribute("ingredientiList", service2.findAllByNome());

        service.create(pizza);
        return "redirect:/pizze";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("pizza", service.getById(id));
        model.addAttribute("ingredientiList", service2.findAllByNome());
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String doEdit(@ModelAttribute("pizza") Pizza pizza, Model model) {
        model.addAttribute("ingredientiList", service2.findAllByNome());
        service.update(pizza);
        return "redirect:/pizze";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        service.deleteById(id);
        return "redirect:/pizze";
    }

}
