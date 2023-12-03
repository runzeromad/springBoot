package com.example.firstproject.dto;

import com.example.firstproject.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // title와 content를 저장하는 생성자가 자동으로 생성됨 (lombok의 어노테이션)
@ToString // toString() 효과
public class CoffeeForm { // DTO(data Transfer Object)폼데이터를 받아 담는 그릇
    private Long id; // 키값
    private String name; // 커피종류를 받는 필드
    private String price; // 커피 가격을 받는 필드

    public Coffee toEntity() { // DTO객체를 엔티티로 반환
        return new Coffee(id, name, price);
    }
}
