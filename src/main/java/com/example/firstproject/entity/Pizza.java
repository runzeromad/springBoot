package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column
    private String name;

    @Column
    private String price;

    public void patch(Pizza pizza){
        System.out.println(pizza.name);
        System.out.println(pizza.price);

        if(pizza.name != null)
            this.name = pizza.name;
        if(pizza.price != null)
            this.price = pizza.price;

    }
}
