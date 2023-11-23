package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // title와 content를 저장하는 생성자가 자동으로 생성됨 (lombok의 어노테이션)
@ToString // toString() 효과
public class ArticleForm { // DTO(data Transfer Object)폼데이터를 받아 담는 그릇
    private String title; // 제목받는 필드
    private String content; // 내용 받는 필드

    /*
    @AllArgsConstructor 대처됨
    // Constructor
    public ArticleForm(String title, String content) { // 전송받은 제목과 내용 저장 하는 생성자
        this.title = title;
        this.content = content;
    }

    @ToString 으로 대처됨
    // toString()
    @Override
    public String toString() { // 데이터를 잘 받았는지 확인할 메서드
        // 출력 결과 ArticleForm{title='제목', content='내용내용내용'}
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
    */

    public Article toEntity() { // DTO객체를 엔티티로 반환
        return new Article(null, title, content);
    }
}
