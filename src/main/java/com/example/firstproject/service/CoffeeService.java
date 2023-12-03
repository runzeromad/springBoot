package com.example.firstproject.service;

import com.example.firstproject.dto.CoffeeForm;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service // 서비스 객체 생성
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository; // 게시글 리파지터리 객체 주입

    public List<Coffee> index() { // GET
        return coffeeRepository.findAll();
    }

    public Coffee show(Long id) { // GET
        return coffeeRepository.findById(id).orElse(null);
    }

    public Coffee create(CoffeeForm dto) { // POST
        Coffee coffee = dto.toEntity();
        if(coffee.getId() != null){
            return null;
        }

        return coffeeRepository.save(coffee);
    }

    public Coffee update(Long id, CoffeeForm dto) { // UPDATE
        // 1. dto --> 엔티티 변환
        Coffee coffee = dto.toEntity();
        log.info("id : {}, article : {}", id, coffee.toString());

        // 2. 데이터 조회
        Coffee target = coffeeRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리하기
        if(target == null || id != coffee.getId()){
            log.info("잘못된 요청! id : {}, arcicle : {}", id, coffee.toString());
            return null;
        }

        // 4. 업데이트
        target.patch(coffee);
        Coffee updated = coffeeRepository.save(target);
        return updated;
    }


    public Coffee delete(Long id) { // DELETE
        // 1. 대상찾기
        Coffee target = coffeeRepository.findById(id).orElse(null);
        // 2. 잘못된 요청 처리하기
        if(target == null){
            log.info("잘못된 요청! id : {}", id);
            return null;
        }
        // 3. 데이터 삭제
        coffeeRepository.delete(target);

        return target; // DB에서 삭제한 대상을 컨트롤러에 반환한다
    }

    @Transactional // 밑의 메서드는 하나의 트랜잭션으로 묶인다
    public List<Coffee> createCoffee(List<CoffeeForm> dtos) {
        // 1. dto 묶음을 엔티티 묶음(스트림 화)으로 변환
        List<Coffee> coffeeList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        // 2. 엔티티 묶음을 DB에 저장
        coffeeList.stream().forEach(coffee -> coffeeRepository.save(coffee));
        // 3. 강제 예외 발생시키키
        coffeeRepository.findById(-1L).orElseThrow(() -> new IllegalArgumentException("커피결제 실패!"));
        // 찾는 데이터가 없으면 예외 발상
        // 4. 결과 값 반환하기
        return  coffeeList;
    }
}
