package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    @Autowired // 스프링 부트가 미리 생성해 놓은 리파지터리 객체 주입
    private ArticleRepository articleRepository;  // articleRepository 객체 선언
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create") // post방식 데이터 전송 맵핑
    public String createArticlesForm(ArticleForm form){ // 메서드 생성 및 반환값 작성 / ArticleForm form : 폼데이터를 DTO로 받기
        System.out.println(form.toString()); // 폼데이터가 잘 담겼는지 출력하여 확인
        // 결과 : ArticleForm{title='제목', content='내용내용내용'}

        // 1. DTO를 entity(엔티티)로 변환
        Article article = form.toEntity(); // toEntity() from객체를 엔티티 객체로 변환
        System.out.println(article.toString()); // DTO가 엔티티로 잘 변환되는지 출력
        // 결과 : Article{id=null, title='제목', content='내용내용내용'}

        // 2. repository(레파지토리)로 entity(엔티티)를 저장
        Article saved = articleRepository.save(article); // article 엔티티를 저장해 saved 객체에 반환
        System.out.println(saved.toString()); // article이 DB에 잘 저장되는지 출력
        // 결과 Article{id=1, title='제목', content='내용내용내용'}

        return "";
    }



}
