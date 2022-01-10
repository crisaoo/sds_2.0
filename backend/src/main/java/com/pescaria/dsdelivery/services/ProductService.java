package com.pescaria.dsdelivery.services;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pescaria.dsdelivery.dto.ProductDTO;
import com.pescaria.dsdelivery.entities.Product;
import com.pescaria.dsdelivery.repositories.ProductRepository;

@Service
@AllArgsConstructor
public class ProductService {
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public List<ProductDTO> findAll(){
		List<Product> list = repository.findAllByOrderByNameAsc();
		return list.stream().map(p -> new ProductDTO(p)).collect(Collectors.toList());
	}
}
