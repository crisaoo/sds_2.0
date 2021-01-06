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
	public OrderDTO insert (OrderDTO objDTO) {
		Order obj = new Order(null, objDTO.getAddress(), objDTO.getLatitude(), objDTO.getLongitude(), Instant.now(), OrderStatus.PENDING);
		
		for (ProductDTO pDTO : objDTO.getProducts()) {
			Product p = prodRepository.getOne(pDTO.getId());
			obj.getProducts().add(p);
		}
		
		return new OrderDTO(repository.save(obj));
	}
}
