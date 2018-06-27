package com.manolo.sell.inventory.domain;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateArticleRequest {

	@NotBlank
	@JsonProperty("description")
	private String description;
	
	@NotBlank
	@JsonProperty("prize")
	private String prize;
	
	public UpdateArticleRequest() {}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrize() {
		return prize;
	}

	public void setPrize(String prize) {
		this.prize = prize;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateArticleRequest [description=").append(description).append(", prize=").append(prize)
				.append("]");
		return builder.toString();
	}
	
	
}
