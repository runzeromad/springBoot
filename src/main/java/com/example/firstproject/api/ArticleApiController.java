package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j // 로그 어노테이션
@RestController // Rest 컨트롤러 선언
public class ArticleApiController {

    // 서비스 계층을 거치는 소스
    @Autowired // 서비스 객체 주입 (생성객체를 가져와 연결)
    private ArticleService articleService;

    // 서비스 클래스를 이용한 CRUD
    // GET : 데이터 조회
    @GetMapping("/api/articles") // URL요청접수
    public List<Article> index(){ // index 메서드 정의
        return articleService.index();
    }

    // GET : 데이터 조회
    @GetMapping("/api/articles/{id}") // URL요청접수(특정 ID가져옴)
    public Article show(@PathVariable Long id){
        return articleService.show(id);
    }

    // POST : 데이터 생성
    @PostMapping("/api/articles")
    // ResponseEntity<Article> 형변환 수정
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){ // dto의 클래스 ArticleForm / @RequestBody 어노테이션은 HTTP 요청의 body 내용(JSON)을 자바 객체로 매핑하는 역할
           Article created = articleService.create(dto);
           return (created != null) ? // 생성 정상 유무에 따른 리턴
                   ResponseEntity.status(HttpStatus.OK).body(created) :
                   ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH : 데이터 수정
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){
        Article updated = articleService.update(id, dto); // 서비스를 통해 글 수정

        return (updated != null) ? // 생성 정상 유무에 따른 리턴
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    // DELETE : 데이터 삭제
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article>  delete(@PathVariable Long id){

        Article deleted = articleService.delete(id);

        return (deleted == null) ?
            ResponseEntity.status(HttpStatus.NO_CONTENT).build() : // build() HTTP응답의 body가 없는 ResponseEntity 객체를 생성 body(updated)과 같은의미
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    // 트렌잭션 테스트
    @PostMapping("/api/transaction-test") // 여러 게시글 생성 요청 접수
    public ResponseEntity<List<Article>> transactionTest (@RequestBody List<ArticleForm> dtos){
        // @RequestBody 어노테이션 : POST요청 시 본문에 실어 보내는 데이터를 transactionTest()의 메서드를 매개 변수로 받아오는 역활
        // 컨트롤러는 요청만 받고 결과만 반환, 실제 작업은 서비스가 함
        List<Article> createdList = articleService.createArticles(dtos); // 서비스 호출
        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();


    }


    /* 서비스 계층을 거치지 않는 경우의 소스
    
    @Autowired // 게시글 리파지터리 주입
    private ArticleRepository articleRepository;

    // GET : 데이터 조회
    @GetMapping("/api/articles") // URL요청접수
    public List<Article> index(){ // index 메서드 정의
        return articleRepository.findAll();
    }
    @GetMapping("/api/articles/{id}") // URL요청접수(특정 ID가져옴)
    public Article show(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }
    // POST : 데이터 생성
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){ // dto의 클래스 ArticleForm / @RequestBody 어노테이션은 HTTP 요청의 body 내용(JSON)을 자바 객체로 매핑하는 역할
        Article article = dto.toEntity(); // dto를 엔티티에 담는다
        return articleRepository.save(article); // 리파지터레에 담아 저장한다
    }

    // PATCH : 데이터 수정
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){ // ArticleForm의 dto는 수정하는 내용(title, content)을 담는다
        // 1. dto --> 엔티티 변환
        Article article = dto.toEntity();
        log.info("id : {}, article : {}", id, article.toString());
        
        // 2. 데이터 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리하기
        if(target == null || id != article.getId()){
            log.info("잘못된 요청! id : {}, arcicle : {}", id, article.toString());
            return ResponseEntity.status(BAD_REQUEST).body(null);
        }

        // 4. 업데이트 및 정상 응답(200)하기
        target.patch(article); // 엔티티에 패치 매서드를 만들어 데이터가 수정한 경우에만 업데이트가 되게 한다
        Article updated = articleRepository.save(target); // article 엔티티 DB에 저장

        return ResponseEntity.status(HttpStatus.OK).body(updated); // http결과 값 전달(응답)
    }


    // DELETE : 데이터 삭제
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article>  delete(@PathVariable Long id){
        // 1. 대상찾기
        Article target = articleRepository.findById(id).orElse(null);
        // 2. 잘못된 요청 처리하기
        if(target == null){
            log.info("잘못된 요청! id : {}", id);
            return ResponseEntity.status(BAD_REQUEST).body(null);
        }
        // 3. 데이터 삭제
        articleRepository.delete(target);

        return ResponseEntity.status(HttpStatus.OK).build(); // build() HTTP응답의 body가 없는 ResponseEntity 객체를 생성 body(updated)과 같은의미
    }
    */
}
