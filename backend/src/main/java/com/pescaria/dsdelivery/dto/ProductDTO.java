package com.pescaria.dsdelivery.dto;

import java.io.Serializable;

import com.pescaria.dsdelivery.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Double price;
	private String description;
	private String imageUri;

	public ProductDTO(Product p) {
		id = p.getId();
		name = p.getName();
		price = p.getPrice();
		description = p.getDescription();
		imageUri = p.getImageUri();
	}
}
