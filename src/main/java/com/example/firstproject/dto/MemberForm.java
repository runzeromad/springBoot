package com.example.firstproject.dto;

import com.example.firstproject.entity.Member;

public class MemberForm { // DTO(data Transfer Object)폼데이터를 받아 담는 그릇
    private String email;
    private String password;

    // Constructor
    public MemberForm(String email, String password) { // 전송받은 제목과 내용 저장 하는 생성자
        this.email = email;
        this.password = password;
    }

    // toString()
    @Override
    public String toString() { // 데이터를 잘 받았는지 확인할 메서드

        return "MemberForm{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    public Member toEntity() { // DTO객체를 엔티티로 반환
        return new Member(null, email, password);
    }
}
