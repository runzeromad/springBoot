package com.example.firstproject.api;

import com.example.firstproject.dto.PizzaForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Pizza;
import com.example.firstproject.service.PizzaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class PizzaApiController {
    @Autowired
    private PizzaService pizzaService;
    // GET 모든 데이터 조회
    @GetMapping("/api/pizza")
    public List<Pizza> index(){
        return pizzaService.index();
    }

    // GET 단일 데이터 조회
    @GetMapping("/api/pizza/{id}")
    public Pizza show(@PathVariable Long id){
        return pizzaService.show(id);
    }

    // POST : 데이터 작성
    @PostMapping("/api/pizza")
    public ResponseEntity<Pizza> create(@RequestBody PizzaForm dto){
        Pizza created = pizzaService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH : 데이터 수정
    @PatchMapping("/api/pizza/{id}")
    public ResponseEntity<Pizza> update(@PathVariable Long id,@RequestBody PizzaForm dto){
        Pizza updated = pizzaService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    // DELETE : 데이터 삭제
    @DeleteMapping("/api/pizza/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Pizza deleted = pizzaService.delete(id);
        return (deleted == null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
