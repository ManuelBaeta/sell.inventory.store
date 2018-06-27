package com.manolo.sell.inventory.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderArticleId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8867824295997158145L;

	@Column(name = "ORDER_ID")
	private Integer orderId;
	
	@Column(name = "ITEM_ID")
	private Integer articleId;
	
	public OrderArticleId() {}
	
	public OrderArticleId(Integer orderId, Integer articleId) {
		this.orderId = orderId;
		this.articleId = articleId;
	}

	public int getOrderId() {
		return orderId;
	}

	public int getArticleId() {
		return articleId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + articleId;
		result = prime * result + orderId;
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
		OrderArticleId other = (OrderArticleId) obj;
		if (articleId != other.articleId)
			return false;
		if (orderId != other.orderId)
			return false;
		return true;
	}
	
	
}
