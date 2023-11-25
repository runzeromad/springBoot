package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // title와 content를 저장하는 생성자가 자동으로 생성됨 (lombok의 어노테이션)
@NoArgsConstructor // 기본생성자 추가 어노테이션
@ToString
@Entity
@Getter // 외부에서 객체의 데이터를 읽을 때 사용하는 어노테이션
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

}
