package com.example.firstproject.api;

import com.example.firstproject.entity.Pizza;
import com.example.firstproject.service.PizzaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class PizzaApiController {
    @Autowired
    private PizzaService pizzaService;
    @GetMapping("/api/pizza")
    public List<Pizza> index(){
        return pizzaService.index();
    }


}
