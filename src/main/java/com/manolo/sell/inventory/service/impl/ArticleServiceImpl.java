package com.manolo.sell.inventory.service.impl;

import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.manolo.sell.inventory.domain.Article;
import com.manolo.sell.inventory.domain.UpdateArticleRequest;
import com.manolo.sell.inventory.domain.exception.EntityNotFoundException;
import com.manolo.sell.inventory.domain.exception.UnableToInsertException;
import com.manolo.sell.inventory.domain.rest.PageRequest;
import com.manolo.sell.inventory.repository.ArticleRepository;
import com.manolo.sell.inventory.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService{

	private final Logger log = LoggerFactory.getLogger(ArticleServiceImpl.class);
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Override
	public Article addArticle(Article article) throws UnableToInsertException {
		log.info("[ArticleService] Adding new Article ");
		try {
			return articleRepository.save(article);
		} catch (Exception ex) {
			log.info("[ArticleService] Unable to add Article. PartNumbr already present: {}. Caught exception, it will "
					+ "be re-thrown as UnableToInsertException", article.getPartNumber());
			throw new UnableToInsertException(String.format("Unable to add Article. PartNumber already present: %s", article.getPartNumber())) ;
		}	
	}

	@Override
	public void updateArticle(int articleId, UpdateArticleRequest request) throws EntityNotFoundException {
		log.info("[ArticleService] Updating article");
		Optional<Article> optArticle = articleRepository.findById(articleId);
		Article article = optArticle
				.orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find article with id: %s", articleId)));
		
		article.setDescription(request.getDescription()).setPrize(request.getPrize());
		articleRepository.save(article);
	}

	@Override
	public void deleteArticle(int articleId) throws EntityNotFoundException {
		log.info("[ArticleService] Deleting user");
		try {
			articleRepository.deleteById(articleId);
		} catch(EmptyResultDataAccessException ex) {
			throw new EntityNotFoundException(String.format("Unable to delete article with id: %s", articleId));
		}
	}

	@Override
	public Article getArticleById(int articleId) throws EntityNotFoundException {
		log.info("[ArticleService] Getting article");
		Optional<Article> optArticle = articleRepository.findById(articleId);
		
		return optArticle.orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find article with id: %s", articleId)));		
	}

	@Override
	public Collection<Article> getArticles(PageRequest pageRequest) {
		log.info("[ArticleService] Getting all users");
		return articleRepository.findAll(org.springframework.data.domain.PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize())).getContent();
	}

}
