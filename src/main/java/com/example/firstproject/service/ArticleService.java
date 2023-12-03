package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service // 서비스 객체 생성
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository; // 게시글 리파지터리 객체 주입

    public List<Article> index() { // GET
        return articleRepository.findAll();
    }

    public Article show(Long id) { // GET
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) { // POST
        Article article = dto.toEntity();
        if(article.getId() != null){
            return null;
        }

        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) { // UPDATE
        // 1. dto --> 엔티티 변환
        Article article = dto.toEntity();
        log.info("id : {}, article : {}", id, article.toString());

        // 2. 데이터 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리하기
        if(target == null || id != article.getId()){
            log.info("잘못된 요청! id : {}, arcicle : {}", id, article.toString());
            return null;
        }

        // 4. 업데이트
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }


    public Article delete(Long id) { // DELETE

        // 1. 대상찾기
        Article target = articleRepository.findById(id).orElse(null);
        // 2. 잘못된 요청 처리하기
        if(target == null){
            log.info("잘못된 요청! id : {}", id);
            return null;
        }
        // 3. 데이터 삭제
        articleRepository.delete(target);

        return target; // DB에서 삭제한 대상을 컨트롤러에 반환한다
    }

    @Transactional // 밑의 메서드는 하나의 트랜잭션으로 묶인다
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // 1. dto 묶음을 엔티티 묶음(스트림 화)으로 변환
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());

        // 2. 엔티티 묶음을 DB에 저장
        articleList.stream().forEach(article -> articleRepository.save(article));

        // 3. 강제 예외 발생시키키
        articleRepository.findById(-1L).orElseThrow(() -> new IllegalArgumentException("결제 실패!"));
        // 찾는 데이터가 없으면 예외 발상

        // 4. 결과 값 반환하기
        return  articleList;
    }
}
