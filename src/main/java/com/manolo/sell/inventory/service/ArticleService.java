package com.manolo.sell.inventory.service;

import java.util.Collection;

import com.manolo.sell.inventory.domain.Article;
import com.manolo.sell.inventory.domain.UpdateArticleRequest;
import com.manolo.sell.inventory.domain.exception.EntityNotFoundException;
import com.manolo.sell.inventory.domain.exception.UnableToInsertException;
import com.manolo.sell.inventory.domain.rest.PageRequest;

public interface ArticleService {

	public Article addArticle(Article article) throws UnableToInsertException;
	
	public void updateArticle(int articleId, UpdateArticleRequest request) throws EntityNotFoundException;
	
	public void deleteArticle(int articleId) throws EntityNotFoundException ;
	
	public Article getArticleById(int articleId) throws EntityNotFoundException ;
	
	public Collection<Article> getArticles(PageRequest request);
	
}
