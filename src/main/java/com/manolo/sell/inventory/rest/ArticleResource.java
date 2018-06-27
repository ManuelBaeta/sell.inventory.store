package com.manolo.sell.inventory.rest;

import java.util.Collection;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.manolo.sell.inventory.domain.Article;
import com.manolo.sell.inventory.domain.UpdateArticleRequest;
import com.manolo.sell.inventory.domain.exception.EntityNotFoundException;
import com.manolo.sell.inventory.domain.exception.UnableToInsertException;
import com.manolo.sell.inventory.domain.rest.PageRequest;
import com.manolo.sell.inventory.service.ArticleService;

@RestController
@RequestMapping("/api/article")
public class ArticleResource {

	private final Logger log = LoggerFactory.getLogger(ArticleResource.class);
	
	@Autowired 
	ArticleService articleService;
	
	@PostMapping()
	public ResponseEntity<Article> addArticle(@Valid @RequestBody Article article) throws UnableToInsertException {
		log.info("Requesting to add article: {}", article);
		Article addedArticle = articleService.addArticle(article);
		return ResponseEntity.status(HttpStatus.CREATED).body(addedArticle);
	}
	
	@PutMapping("/{articleId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateArticle(@PathVariable int articleId, @Valid @RequestBody UpdateArticleRequest updateArticleRequest) throws EntityNotFoundException {
		log.info("Requesting to update article with id {}, with values: {}", articleId, updateArticleRequest);
		articleService.updateArticle(articleId, updateArticleRequest);
	}
	
	@DeleteMapping("/{articleId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteArticle (@PathVariable int articleId) throws EntityNotFoundException {
		log.info("Requesting to delete article with id: {}", articleId);
		articleService.deleteArticle(articleId);
	}
	
	@GetMapping("/{articleId}")
	public Article getArticleById(@PathVariable int articleId) throws EntityNotFoundException {
		log.info("Requesting  article with id: {}", articleId);
		return articleService.getArticleById(articleId);
	}
	
	@GetMapping()
	public Collection<Article> getArticles(@RequestParam(name="pageNumber", defaultValue="0") int pageNumber, @RequestParam(name="pagesize", defaultValue="5") int pageSize) {
		log.info("Requesting all articles. Page number: {}, page size: {}", pageNumber, pageSize );
		return articleService.getArticles(new PageRequest(pageNumber, pageSize));
	}
}
