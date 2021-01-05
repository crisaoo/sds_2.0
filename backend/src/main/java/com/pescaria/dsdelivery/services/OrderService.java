package com.pescaria.dsdelivery.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pescaria.dsdelivery.entities.Order;
import com.pescaria.dsdelivery.entities.OrderStatus;
import com.pescaria.dsdelivery.repositories.OrderRepository;
import com.pescaria.dsdelivery.dto.OrderDTO;

@Service
public class OrderService {
	@Autowired
	private OrderRepository repository;
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		List<Order> list = repository.findAllPendingOrderOrderByMomentAsc();
		return list.stream().map(o -> new OrderDTO(o)).collect(Collectors.toList());
	}
}
