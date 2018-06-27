package com.manolo.sell.inventory.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.manolo.sell.inventory.domain.rest.ArticleOrdered;

@Entity
@Table(name="ORDER_ARTICLE")
public class OrderArticle {

	@EmbeddedId
	private OrderArticleId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    private Order order;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("articleId")
    private Article article;
    
    private Integer quantity;
    
	public OrderArticle() {}
	
	public OrderArticle(Order order, Article article) {
		this.order = order;
		this.article = article;
		this.id = new OrderArticleId(order.getOrderId(), article.getArticleId());
	}

	public OrderArticle(ArticleOrdered article) {
		this.quantity = article.getQuantity();
		this.article = new Article(article.getArticleId());
	}

	public OrderArticleId getId() {
		return id;
	}

	public void setId(OrderArticleId id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		OrderArticle other = (OrderArticle) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderArticle [id=").append(id).append(", order=").append(order).append(", article=")
				.append(article).append(", quantity=").append(quantity).append("]");
		return builder.toString();
	}
	
	
}
