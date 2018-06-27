package com.manolo.sell.inventory.domain.rest;

public class PageRequest {
	
	private int pageNumber;
	
	private int pageSize;
	
	public PageRequest(int pageNumber, int pageSize) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PageRequest [pageNumber=").append(pageNumber).append(", pageSize=").append(pageSize)
				.append("]");
		return builder.toString();
	}
	
	
}
