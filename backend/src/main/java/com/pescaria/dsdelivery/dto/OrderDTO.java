package com.pescaria.dsdelivery.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.pescaria.dsdelivery.entities.Order;
import com.pescaria.dsdelivery.entities.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO implements Serializable{
	private final static long serialVersionUID = 1L;
	
	private Long id;
	private String address;
	private Double latitude;
	private Double longitude;
	private Instant moment;
	private OrderStatus status;
	private List<ProductDTO> products = new ArrayList<>();

	public OrderDTO(Order o) {
		id = o.getId();
		address = o.getAddress();
		latitude = o.getLatitude();
		longitude = o.getLongitude();
		moment = o.getMoment();
		status = o.getStatus();
		products = o.getProducts().stream().map(p -> new ProductDTO(p)).collect(Collectors.toList());
	}
}
