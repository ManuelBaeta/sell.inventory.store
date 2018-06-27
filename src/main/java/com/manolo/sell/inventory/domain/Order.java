package com.manolo.sell.inventory.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.manolo.sell.inventory.domain.rest.OrderRequest;

@Entity
@Table(name="ORDERS")
public class Order {

	@JsonProperty("order-id")
	@Column(name="ORDER_ID", nullable=false)
	@SequenceGenerator(name="Order_Gen", sequenceName="ORDER_SEQ")
	@Id @GeneratedValue(generator="Order_Gen")
	private Integer orderId;
	
	@JsonProperty("requested-at")
	@JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="REQUESTED_AT", nullable=false)
	private LocalDateTime requestedAt;
	
	@JsonProperty("to-be-delivered-at")
	@JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="TO_BE_DELIVERED_AT", nullable=false)
	private LocalDateTime toBeDeliveredAt;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	
    @OneToMany(mappedBy = "order", cascade= {CascadeType.PERSIST})
	private Collection<OrderArticle> articles = new ArrayList<>();
	
	public Order() {}
	
	public Order(int orderId) {
		this.orderId = orderId;
	}
	
	public void addArticle(Article article) {
		OrderArticle orderArticle = new OrderArticle(this, article);
		articles.add(orderArticle);
		article.getOrders().add(orderArticle);
	}
	
	public void removeArticle(Article article) {
		
	}
	
	public Collection<OrderArticle> getArticles() {
		return articles;
	}

	public void setArticles(Collection<OrderArticle> articles) {
		this.articles = articles;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public void setRequestedAt(LocalDateTime requestedAt) {
		this.requestedAt = requestedAt;
	}

	public void setToBeDeliveredAt(LocalDateTime toBeDeliveredAt) {
		this.toBeDeliveredAt = toBeDeliveredAt;
	}

	public static Order fromOrderRequest(OrderRequest orderRequest, User user) {
		Order order = new Order();
		order.user = user;
		order.articles = orderRequest.getArticles().stream()
							.map(article -> new OrderArticle(article))
							.collect(Collectors.toList());
		order.requestedAt = LocalDateTime.now();
		order.toBeDeliveredAt = LocalDateTime.now().plusWeeks(1);
		
		return order;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Order [orderId=").append(orderId).append(", requestedAt=").append(requestedAt)
				.append(", toBeDeliveredAt=").append(toBeDeliveredAt).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		return true;
	}
	
	
}
