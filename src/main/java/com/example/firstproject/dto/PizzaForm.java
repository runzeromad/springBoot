package com.example.firstproject.dto;

import com.example.firstproject.entity.Pizza;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class PizzaForm {
    private Long id;
    private String name;
    private String price;

    public Pizza toEntity(){
        return new Pizza(id, name, price);
    }
}
