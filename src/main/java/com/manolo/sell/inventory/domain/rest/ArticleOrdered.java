package com.manolo.sell.inventory.domain.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ArticleOrdered {

	@JsonProperty("article-id")
	private int articleId;

	@JsonProperty("quantity")
	private int quantity;
	
	public ArticleOrdered() {}

	public int getArticleId() {
		return articleId;
	}

	public int getQuantity() {
		return quantity;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ArticleOrdered [articleId=").append(articleId).append(", quantity=").append(quantity)
				.append("]");
		return builder.toString();
	}
	
	
}
