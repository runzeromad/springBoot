package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository <Article, Long>{ // CrudRepository 부모 -> ArticleRepository 자식 상속(extends)관계
    @Override
    ArrayList<Article> findAll();
}
