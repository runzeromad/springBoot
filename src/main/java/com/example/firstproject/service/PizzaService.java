package com.example.firstproject.service;

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

}
