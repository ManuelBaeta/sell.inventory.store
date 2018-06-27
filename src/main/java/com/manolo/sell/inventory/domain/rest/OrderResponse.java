package com.manolo.sell.inventory.domain.rest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderResponse {

	@JsonProperty("order-id")
	private int orderId;
	
	@JsonProperty("requested-at")
	private LocalDateTime requestedAt;
	
	@JsonProperty("to-be-delivered-at")
	private LocalDateTime toBeDeliveredAt;
	
	@JsonProperty("articles")
	private Collection<ArticleOrdered> articles = new ArrayList<>();
	
	public OrderResponse() {}

	public static OrderResponse giveMeDefault(OrderRequest request) {
		OrderResponse response = new OrderResponse();
		response.articles = request.getArticles();
		response.orderId = 101;
		response.requestedAt = LocalDateTime.now();
		response.toBeDeliveredAt = LocalDateTime.now();
		return response;
	}
	
	public int getOrderId() {
		return orderId;
	}

	public LocalDateTime getRequestedAt() {
		return requestedAt;
	}

	public LocalDateTime getToBeDeliveredAt() {
		return toBeDeliveredAt;
	}

	public Collection<ArticleOrdered> getArticles() {
		return articles;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderResponse [orderId=").append(orderId).append(", requestedAt=").append(requestedAt)
				.append(", toBeDeliveredAt=").append(toBeDeliveredAt).append(", articles=").append(articles)
				.append("]");
		return builder.toString();
	}
	
}
