package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest // 해당 클래스를 스프링 부트와 연동해 통합 테스트를 수행하겠다고 선언하는것
class ArticleServiceTest {
    @Autowired
    ArticleService articleService; // articlesService 객체 주입
    @Test
    void index() {
        // 1. 예상데이터 작성하기
        Article a = new Article(1L,"가가가가","111111"); // 예상데이터 객체로 저장
        Article b = new Article(2L,"나나나나","222222"); // 예상데이터 객체로 저장
        Article c = new Article(3L,"다다다다","333333"); // 예상데이터 객체로 저장
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c)); // a,b,c 합치기
        // Arrays.asList()메서드로 합친 정적 리스트를 new ArrayList로 만들어 expected에 담음
        
        // Arrays.asList()는 입력된 배열 또는 2개 이상의 동일한 타입 데이터를 정적 리스트로 만들어 반환
        // 정적 리스트는 고정 크기이므로 add()나 remove()를 사용할수 없다
        // 정적 리스트에서 add()나 remove()를 사용하려면 정적 리스트를 일반 리스트로 새로 만들어야 한다

        // 2. 실제 데이터 획득하기
        List<Article> articles = articleService.index();

        // 3. 예상 데이터와 실제 데이터 비교 검증하기
        assertEquals(expected.toString(), articles.toString());

    }
    @Test
    void show_성공_존재하는_id로_입력() { // success
        // 1. 예상데이터 작성하기
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "111111"); // 예상 데이터 작성 저장
        // 2. 실제 데이터 획득하기
        Article article = articleService.show(id); // 실 데이터 불러와 저장
        // 3. 예상데이터와 실제 데이터 비교 검증하기
        assertEquals(expected.toString(), article.toString()); // 두 데이터 비교해서 판단
    }
    @Test
    void show_실패_존재하지_않는_id로_입력() { // success
        // 1. 예상데이터 작성하기
        Long id = -1L; // 존재 하지 않는 데이터
        Article expected = null; // 오류 데이터 작성
        // 2. 실제 데이터 획득하기
        Article article = articleService.show(id); // 실 데이터 불러와 저장
        // 3. 예상데이터 / 실제 데이터 비교 검증하기
        assertEquals(expected, article); // 두 데이터 비교해서 판단

        // 잘통과됨 존재하지 않는 데이터가 있을경우 null을 반환하고 예상 데이터도 null을 반환 하기 때문
    }
    @Test
    @Transactional
    void create_성공_title과_content만_있는_dto_입력() {
        // 1. 예상데이터 입력
        String title = "라라라라";
        String content = "4444";
        Article expected = new Article(4L, title, content); // 예상 데이터
        // 2. 실제 데이터 입력
        ArticleForm dto = new ArticleForm(null, title, content); // 실제 데이터
        Article article = articleService.create(dto);
        // 3. 데이터 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    @Transactional
    void create_실패_id가_포함된_dto_입력() {
        // 1. 예상데이터 입력
        Long id = 4L;
        String title = "라라라라";
        String content = "4444";
        Article expected = null; // 예상 데이터
        // 2. 실제 데이터 입력
        ArticleForm dto = new ArticleForm(id, title, content); // 실제 데이터
        Article article = articleService.create(dto);
        // 3. 데이터 비교 및 검증
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void update를_성공존재하는_id와_title_content가_있는_dto_입력(){
        // 1. 예상데이터 입력
        Long id = 2L;
        String title = "나나나나1111";
        String content = "2222223333";
        Article expected = new Article(id, title, content); // 예상 데이터
        // 2. 실제 데이터 입력
        ArticleForm dto = new ArticleForm(id, title, content); // 실제 데이터
        Article article = articleService.update(id,dto);
        // 3. 데이터 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update를_성공존재하는_id와_title만_있는_dto_입력(){
        // 1. 예상데이터 입력
        Long id = 2L;
        String title = "가나다라마바사";
        String content = null;
        Article expected = new Article(id, "가나다라마바사", "222222"); // 예상 데이터
        // 2. 실제 데이터 입력
        ArticleForm dto = new ArticleForm(id, title, content); // 실제 데이터
        Article article = articleService.update(id, dto);
        // 3. 데이터 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update를_성공존재하는_id의_dto_입력(){
        // 1. 예상데이터 입력
        Long id = -1L;
        String title = "AAAAA";
        String content = "test111";
        Article expected = null; // 예상 데이터
        // 2. 실제 데이터 입력
        ArticleForm dto = new ArticleForm(id, title, content); // 실제 데이터
        Article article = articleService.update(id, dto);
        // 3. 데이터 비교 및 검증
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void delete_존재하는_id입력_성공한경우(){
        // 1. 예상데이터 입력
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "111111");
        // 2. 실제 데이터 입력
        Article article = articleService.delete(id);
        // 3. 데이터 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }


    @Test
    @Transactional
    void delete_존재하지않는_id입력_실패한경우(){
        // 1. 예상데이터 입력
        Long id = -1L;
        Article expected = null;
        // 2. 실제 데이터 입력
        Article article = articleService.delete(id);
        // 3. 데이터 비교 및 검증
        assertEquals(expected, article);
    }

}