package com.manolo.sell.inventory.service.impl;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manolo.sell.inventory.domain.Article;
import com.manolo.sell.inventory.domain.Order;
import com.manolo.sell.inventory.domain.User;
import com.manolo.sell.inventory.domain.exception.EntityForeignKeyNotFound;
import com.manolo.sell.inventory.domain.exception.EntityNotFoundException;
import com.manolo.sell.inventory.domain.rest.OrderRequest;
import com.manolo.sell.inventory.domain.rest.OrderResponse;
import com.manolo.sell.inventory.repository.ArticleRepository;
import com.manolo.sell.inventory.repository.OrderRepository;
import com.manolo.sell.inventory.repository.UserRepository;
import com.manolo.sell.inventory.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	private final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired 
	OrderRepository orderRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Override
	public OrderResponse placeOrder(OrderRequest orderRequest) throws EntityNotFoundException, EntityForeignKeyNotFound {
		log.info("[OrderService] Adding new Order ");
		
		Optional<User> optUser = userRepository.findById(orderRequest.getUserId());
		User user = optUser.orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find user with id: %s", orderRequest.getUserId())));
		
		Collection<Article> articles = 
				articleRepository.findAllById(orderRequest.getArticles().stream().map((art) -> art.getArticleId()).collect(Collectors.toList()));
		
		if (articles.size() != orderRequest.getArticles().size())
			throw new EntityForeignKeyNotFound(String.format("Missing articles definition. Ids: %s", orderRequest.getArticles()));
		
		Order orderToAdd = Order.fromOrderRequest(orderRequest, user);
		orderRepository.save(orderToAdd);
		return null;
	}

}
