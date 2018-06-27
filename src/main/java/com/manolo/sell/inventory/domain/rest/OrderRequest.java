package com.manolo.sell.inventory.domain.rest;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderRequest {

	@JsonIgnore
	private int userId;
	
	@JsonProperty("articles")
	private Collection<ArticleOrdered> articles = new ArrayList<>();
	
	public OrderRequest() {}

	public int getUserId() {
		return userId;
	}

	public Collection<ArticleOrdered> getArticles() {
		return articles;
	}

	public OrderRequest setUserId(int userId) {
		this.userId = userId;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderRequest [userId=").append(userId).append(", articles=").append(articles).append("]");
		return builder.toString();
	}
	
	
}
