package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // 해당클래스를 JPA와 연동해 테스트 하겠다는 선언
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;  // 레파지터리 객체 주입
    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회 ID기준") // 메서드 명은 그대로 둔채 테스트 이름만 바꾸고 싶을경우
    void finByArticleId() {
        // case 1 : 4번 게시글의 모든 댓글 조회
        {
            // 1. 입력 데이터 준비
            Long articleId = 4L;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.finByArticleId(articleId);
            // 3. 예상 데이터
            // 부모글 객체 생성
            Article article = new Article(4L, "당신의 인생영화는?","댓글 고");
            // 댓글 객체 생성
            Comment a = new Comment(1L, article, "굿윌헌팅", "park");
            Comment b = new Comment(2L, article, "아이엠샘", "kim");
            Comment c = new Comment(3L, article, "쇼생크탈출", "choi");

            List<Comment> expected = Arrays.asList(a, b, c); // 댓글 합치기

            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글 출력!");
        }
    }

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회 nickname기준")
    void findByNickname() {
        // 1. 입력 데이터 준비
        String nickname = "park";
        // 2. 실제 데이터
        List<Comment> comments = commentRepository.findByNickname(nickname);
        // 3. 예상 데이터
        // 댓글 객체 생성
        Comment a = new Comment(1L, new Article(4L, "당신의 인생영화는?","댓글 고"),"굿윌헌팅", nickname);
        Comment b = new Comment(4L, new Article(5L, "당신의 소울 푸드는?","댓글 고고"),"치킨", nickname);
        Comment c = new Comment(7L, new Article(6L, "당신의 취미는?","댓글 고고고"),"조깅", nickname);

        List<Comment> expected = Arrays.asList(a, b, c); // 댓글 합치기

        // 4. 비교 및 검증
        assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글 출력!");

    }
}