package com.manolo.sell.inventory.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="ARTICLES")
public class Article {

	@JsonProperty("article-id")
	@Column(name="ARTICLE_ID", nullable=false)
	@SequenceGenerator(name="Article_Gen", sequenceName="ARTICLE_SEQ")
	@Id @GeneratedValue(generator="Article_Gen")
	private int articleId;
	
	@NotBlank
	@JsonProperty("part-number")
	@Column(name="PART_NUMBER", unique = true, nullable=false)
	private String partNumber;
	
	@NotBlank
	@JsonProperty("description")
	@Column(name="DESCRIPTION", nullable=false)
	private String description;
	
	@NotBlank
	@JsonProperty("prize")
	@Column(name="prize", nullable=false)
	private String prize;
	
    @OneToMany(mappedBy = "article")
	private Collection<OrderArticle> orders = new ArrayList<>();
	
	public Article () {}
	
	public Article(int articleId) {
		this.articleId = articleId;
	}

	public Article simulateInsert() {
		this.articleId = new Random().nextInt();
		return this;
	}
	
	public Collection<OrderArticle> getOrders() {
		return orders;
	}

	public void setOrders(Collection<OrderArticle> orders) {
		this.orders = orders;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public static Article giveMeDefaultArticle() {
		Article newArticle = new Article();
		newArticle.articleId = 201;
		newArticle.description = "default-article-description";
		newArticle.partNumber = "default-part-number";
		newArticle.prize = "default-prize";
		return newArticle;
	}
	
	public int getArticleId() {
		return articleId;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public String getDescription() {
		return description;
	}

	public String getPrize() {
		return prize;
	}

	public Article setDescription(String description) {
		this.description = description;
		return this;
	}

	public Article setPrize(String prize) {
		this.prize = prize;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Article [articleId=").append(articleId).append(", partNumber=").append(partNumber)
				.append(", description=").append(description).append(", prize=").append(prize).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + articleId;
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
		Article other = (Article) obj;
		if (articleId != other.articleId)
			return false;
		return true;
	}
	
	
	
}
