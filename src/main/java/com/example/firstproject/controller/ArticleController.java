package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j // 로깅 기능을 사용위한 어노테이션 (lombok의 어노테이션)
@Controller
public class ArticleController {
    @Autowired // 스프링 부트가 미리 생성해 놓은 리파지터리 객체 주입
    private ArticleRepository articleRepository;  // articleRepository 객체 선언

    // Write
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    // Insert
    @PostMapping("/articles/create") // post방식 데이터 전송 맵핑
    public String createArticles(ArticleForm form){ // 메서드 생성 및 반환값 작성 / ArticleForm form : 폼데이터를 DTO로 받기

        log.info(form.toString()); // 로깅기능 사용 System.out.println를 대처

        // 1. DTO를 entity(엔티티)로 변환
        Article article = form.toEntity(); // toEntity() from객체를 엔티티 객체로 변환
        log.info(article.toString());

        // 2. repository(레파지토리)로 entity(엔티티)를 저장
        Article saved = articleRepository.save(article); // article 엔티티를 저장해 saved 객체에 반환
        log.info(saved.toString());

        return "";
    }


    // VIEW
    @GetMapping("/articles/{id}") // 데이터 조회 요청 접수
    public String show(@PathVariable Long id, Model model){ // 매개변수로 id 받아오기 {id} --> id
        // @PathVariable URL 요청으로 들어온 전달값을 컨트롤러의 매개변수로 가져오는 어노테이션
        log.info("id = " + id);

        // 1. id 조회해서 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null); // orElse(null) : id값이 없을경우 null을 반환한다는 의미

        // 2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);

        // 3. 뷰 페이지에 반환하기
        return "articles/show";
    }

    // LIST
    @GetMapping("/articles")
    public String index(Model model){
        // 1. DB에서 모든 데이터 가져오기
        List<Article> articleEntityList = articleRepository.findAll();
        // List가 ArrayList의 상위 타입 임으로 위와 같이 해도 상관은 없다 / 정확한 표현은 -> ArrayList<Article> articleEntityList = articleRepository.findAll();

        // 2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);
        // 3. 사용자에게 보여 줄 뷰 페이지 반환 하기
        return "articles/index";
    }
}
