package com.example.firstproject.dto;

import com.example.firstproject.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString // loombok로 대처
@AllArgsConstructor // 클래스 안쪽의 모든 필드를 매개 변수로 하는 생성자를 만드는 어노테이션
public class MemberForm { // DTO(data Transfer Object)폼데이터를 받아 담는 그릇
    private String email;
    private String password;

    public Member toEntity() { // DTO객체를 엔티티로 반환
        return new Member(null, email, password);
    }
}
