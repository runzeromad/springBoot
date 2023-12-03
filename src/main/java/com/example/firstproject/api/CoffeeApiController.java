package com.example.firstproject.api;


import com.example.firstproject.dto.CoffeeForm;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j // 로그 어노테이션
@RestController // Rest 컨트롤러 선언
public class CoffeeApiController {

    @Autowired // 게시글 리파지터리 주입
    private CoffeeRepository coffeeRepository;

    // GET : 데이터 조회
    @GetMapping("/api/coffees")
    public List<Coffee> index(){ // index 메서드 정의
        return coffeeRepository.findAll();
    }
    @GetMapping("/api/coffees/{id}")
    public Coffee show(@PathVariable Long id){
        return coffeeRepository.findById(id).orElse(null);
    }
    // POST : 데이터 생성
    @PostMapping("/api/coffees")
    public Coffee create(@RequestBody CoffeeForm dto){
        Coffee coffee = dto.toEntity(); // dto를 엔티티에 담는다
        return coffeeRepository.save(coffee); // 리파지터레에 담아 저장한다
    }

    // PATCH : 데이터 수정
    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeForm dto){ // ArticleForm의 dto는 수정하는 내용(name, price)을 담는다
        // 1. dto --> 엔티티 변환
        Coffee coffee = dto.toEntity();
        log.info("id : {}, article : {}", id, coffee.toString());
        
        // 2. 데이터 조회
        Coffee target = coffeeRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리하기
        if(target == null || id != coffee.getId()){
            log.info("잘못된 요청! id : {}, coffee : {}", id, coffee.toString());
            return ResponseEntity.status(BAD_REQUEST).body(null);
        }

        // 4. 업데이트 및 정상 응답(200)하기
        target.patch(coffee); // 엔티티에 패치 매서드를 만들어 데이터가 수정한 경우에만 업데이트가 되게 한다
        Coffee updated = coffeeRepository.save(target); // article 엔티티 DB에 저장

        return ResponseEntity.status(HttpStatus.OK).body(updated); // http결과 값 전달(응답)
    }


    // DELETE : 데이터 삭제
    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id){
        // 1. 대상찾기
        Coffee target = coffeeRepository.findById(id).orElse(null);
        // 2. 잘못된 요청 처리하기
        if(target == null){
            log.info("잘못된 요청! id : {}", id);
            return ResponseEntity.status(BAD_REQUEST).body(null);
        }
        // 3. 데이터 삭제
        coffeeRepository.delete(target);

        return ResponseEntity.status(HttpStatus.OK).build(); // build() HTTP응답의 body가 없는 ResponseEntity 객체를 생성 body(updated)과 같은의미
    }

}
