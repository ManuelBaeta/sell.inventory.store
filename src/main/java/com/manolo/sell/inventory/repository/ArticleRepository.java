package com.manolo.sell.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manolo.sell.inventory.domain.Article;

public interface ArticleRepository  extends JpaRepository<Article, Integer> {

}
