package com.example.firstproject.api;


import com.example.firstproject.dto.CoffeeForm;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j // 로그 어노테이션
@RestController // Rest 컨트롤러 선언
public class CoffeeApiController {

    // 서비스 계층을 거치는 소스
    @Autowired // 서비스 객체 주입 (생성객체를 가져와 연결)
    private CoffeeService coffeeService;

    // 서비스 클래스를 이용한 CRUD
    // GET : 데이터 조회
    @GetMapping("/api/coffees") // URL요청접수
    public List<Coffee> index(){ // index 메서드 정의
        return coffeeService.index();
    }

    // GET : 데이터 조회
    @GetMapping("/api/coffees/{id}") // URL요청접수(특정 ID가져옴)
    public Coffee show(@PathVariable Long id){
        return coffeeService.show(id);
    }

    // POST : 데이터 생성
    @PostMapping("/api/coffees")
    // ResponseEntity<Coffee> 형변환 수정
    public ResponseEntity<Coffee> create(@RequestBody CoffeeForm dto){ // dto의 클래스 ArticleForm / @RequestBody 어노테이션은 HTTP 요청의 body 내용(JSON)을 자바 객체로 매핑하는 역할
        Coffee created = coffeeService.create(dto);
        return (created != null) ? // 생성 정상 유무에 따른 리턴
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH : 데이터 수정
    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeForm dto){
        Coffee updated = coffeeService.update(id, dto); // 서비스를 통해 글 수정
        return (updated != null) ? // 생성 정상 유무에 따른 리턴
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // DELETE : 데이터 삭제
    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee>  delete(@PathVariable Long id){
        Coffee deleted = coffeeService.delete(id);
        return (deleted == null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() : // build() HTTP응답의 body가 없는 ResponseEntity 객체를 생성 body(updated)과 같은의미
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    // 트렌잭션 테스트
    @PostMapping("/api/transaction-test2") // 여러 게시글 생성 요청 접수
    public ResponseEntity<List<Coffee>> transactionTest (@RequestBody List<CoffeeForm> dtos){
        // @RequestBody 어노테이션 : POST요청 시 본문에 실어 보내는 데이터를 transactionTest()의 메서드를 매개 변수로 받아오는 역활
        // 컨트롤러는 요청만 받고 결과만 반환, 실제 작업은 서비스가 함
        List<Coffee> coffeeList = coffeeService.createCoffee(dtos); // 서비스 호출
        return (coffeeList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(coffeeList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
