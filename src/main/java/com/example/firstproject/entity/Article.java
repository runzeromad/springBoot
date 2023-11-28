package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor  // title와 content를 저장하는 생성자가 자동으로 생성됨 (lombok의 어노테이션)
@NoArgsConstructor // 기본생성자 추가 어노테이션
@ToString // toString() 메서드 대체
@Entity // 데이터베이스의 테이블과 일대일로 매칭되는 객체 단위 Entity객체의 인스턴스 하나가 테이블에서 하나의 레코드 값을 의미한다
@Getter // 외부에서 객체의 데이터를 읽을 때 사용하는 어노테이션
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id를 순차적으로 생성해줌
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    /*
    // @AllArgsConstructor 로 대처됨
    // Article 생성자 추가
    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    // @ToString로 대처됨
    // toString() 메서드 추가
    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    // @NoArgsConstructor로 대처됨
    // 기본 생성자 코드
    Article(){

    }

    // id 반환 메서드(getter) @Getter로 대체됨
    public Long getId() {
        return id;
    }

     */
}