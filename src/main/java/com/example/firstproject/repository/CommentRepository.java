package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    // 특정 게시글의 모든 댓글 조회
    // 네이티브 쿼리 메소드 : 형식 > @Query(value = "쿼리", nativeQuery = true)
    @Query(value =
            "SELECT * " +
            "from comment " +
            "where article_id = :articleId",
            nativeQuery = true
    ) // value = 에 실행쿼리 작성, where 문의 = 에 :(세미콜론)을 꼭 명시 해줘야함
    List<Comment> finByArticleId(Long articleId);

    // 특정 닉네임의 모든 댓글 조회
    List<Comment> findByNickname(String nickname);
    // 네이티브 쿼리 XML


}

