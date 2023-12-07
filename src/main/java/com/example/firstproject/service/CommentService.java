package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        // 1. 댓글 조회
        // articleId에 해당하는 모든 댓글을 가져와 엔티티의 List<Comment> comments에 담는다
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        // 2. 엔티티 -> DTO변환
        // 새로운 객체(ArrayList<CommentDto>()) 를 생성하여 List<CommentDto> dtos에 담는다
        List<CommentDto> dtos = new ArrayList<CommentDto>();

        /* for 문법
        for (int i = 0; i < comments.size(); i++) { // 조회 댓글 엔티티 수만큼 반복하기
            Comment c =comments.get(i); // 조회한 댓글 엔티티 하나씩 자져오기
            CommentDto dto = CommentDto.createCommentDto(c); // 엔티티를 DTO로 변환
            dtos.add(dto); // 변환한 DTO를 dtos 리스트에 삽입
        }
        // 3. 결과 변환
        return dtos;
        */

        // stream 문법
        // 3. 결과반환
        return commentRepository.findByArticleId(articleId)
                .stream() // stream() : 컬렉션이나 리스트에 저장된 요소들을 하나씩 참조하며 반복 처리할 때 사용
                .map(comment -> CommentDto.createCommentDto(comment)) // 스트림화한 댓글 엔티티 목록을 DTO로 변환(map 매서드 이용) : .map(a->b) 스트림의 각요소 a를 꺼내 b를 수행한 결과로 매빙 
                .collect(Collectors.toList()); // 스트림 데이터를 동일한 리스트 데이터로 형변환
                // collect() : 메서드의 반환형을 변경할수 있다
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        // 1. 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));
        // orElseThrow() : Optional 객체로 값이 존재하면 그 값을 반환하고, 값이 존재하지 않으면 전달값으로 보낸 예외를 발생시키는 메서드
        // IllegalArgumentException : 클래스로 메서드가 잘못됐거나 부적합한 전달 값을 보냈음을 나타냄

        // 2. 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);
        // 3. 댓글 엔티티를 DB에 저장
        Comment created = commentRepository.save(comment);
        // 4. DTO로 변환해 변환
        return  CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        // 1. 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));
        // 2. 댓글 수정
        target.patch(dto);
        // 3. DB로 갱신
        Comment updated = commentRepository.save(target);
        // 4. 댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    public CommentDto delete(Long id) {
        // 1. 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 삭제 댓글이 없습니다."));
        // 2. DB 데이터 삭제
        commentRepository.delete(target);
        return CommentDto.createCommentDto(target);
    }
}
