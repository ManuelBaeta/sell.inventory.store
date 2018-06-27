package com.manolo.sell.inventory.service;

import com.manolo.sell.inventory.domain.exception.EntityForeignKeyNotFound;
import com.manolo.sell.inventory.domain.exception.EntityNotFoundException;
import com.manolo.sell.inventory.domain.rest.OrderRequest;
import com.manolo.sell.inventory.domain.rest.OrderResponse;

public interface OrderService {

	public OrderResponse placeOrder(OrderRequest orderRequest) throws EntityNotFoundException, EntityForeignKeyNotFound;
	
	
}
