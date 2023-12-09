package com.example.firstproject.service;

import com.example.firstproject.dto.PizzaForm;
import com.example.firstproject.entity.Pizza;
import com.example.firstproject.repository.PizzaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;

    public List<Pizza> index(){
        return pizzaRepository.findAll();
    }

    public Pizza show(Long id){
        return pizzaRepository.findById(id).orElse(null);
    }

    public Pizza create(PizzaForm dto){
        Pizza pizza = dto.toEntity();
        if(pizza.getId() != null){
            return null;
        }

        return pizzaRepository.save(pizza);
    }

    public Pizza update(Long id, PizzaForm dto){
        Pizza pizza = dto.toEntity();
        Pizza target = pizzaRepository.findById(id).orElse(null);

        if(target == null || id != pizza.getId()){
            return null;
        }

        target.patch(pizza);
        Pizza updated = pizzaRepository.save(target);
        return updated;
    }

    public Pizza delete(Long id){
        Pizza target = pizzaRepository.findById(id).orElse(null);
        if(target == null){
            return null;
        }
        pizzaRepository.delete(target);
        return target;
    }
}
