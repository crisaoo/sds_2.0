package com.pescaria.dsdelivery.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pescaria.dsdelivery.dto.OrderDTO;
import com.pescaria.dsdelivery.dto.ProductDTO;
import com.pescaria.dsdelivery.entities.Order;
import com.pescaria.dsdelivery.entities.OrderStatus;
import com.pescaria.dsdelivery.entities.Product;
import com.pescaria.dsdelivery.repositories.OrderRepository;
import com.pescaria.dsdelivery.repositories.ProductRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository repository;
	@Autowired
	private ProductRepository prodRepository;
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		List<Order> list = repository.findAllPendingOrderOrderByMomentAsc();
		return list.stream().map(o -> new OrderDTO(o)).collect(Collectors.toList());
	}
	
	@Transactional
	public OrderDTO insert (OrderDTO dto) {
		Order obj = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(), Instant.now(), OrderStatus.PENDING);
		
		for (ProductDTO prodDto : dto.getProducts()) {
			Product p = prodRepository.getOne(prodDto.getId());
			obj.getProducts().add(p);
		}
		
		return new OrderDTO(repository.save(obj));
	}
	
	@Transactional
	public OrderDTO setDelivered (Long id) {
		Order obj = repository.getOne(id);
		if (obj.getStatus().equals(OrderStatus.PENDING)) {
			obj.setStatus(OrderStatus.DELIVERED);
			obj = repository.save(obj);
		}
		return new OrderDTO(obj);
	}
}
