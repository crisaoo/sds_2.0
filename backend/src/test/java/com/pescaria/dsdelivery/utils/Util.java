package com.pescaria.dsdelivery.utils;

import com.pescaria.dsdelivery.dto.OrderDTO;
import com.pescaria.dsdelivery.dto.ProductDTO;
import com.pescaria.dsdelivery.entities.Order;
import com.pescaria.dsdelivery.entities.OrderStatus;
import com.pescaria.dsdelivery.entities.Product;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public abstract class Util {
    // Auxiliary methods
    public static List<Order> getOrders(){
        Order o1 = new Order(1L, "address 1", -11.7545, -30.5697, Instant.now(), OrderStatus.PENDING);
        Order o2 = new Order(2L, "address 2", -11.7545, -30.5697, Instant.now(), OrderStatus.DELIVERED);
        Order o3 = new Order(3L, "address 3", -11.7545, -30.5697, Instant.now(), OrderStatus.PENDING);

        return Arrays.asList(o1, o2, o3);
    }

    public static List<OrderDTO> getOrdersDTO(){
        Order o1 = new Order(1L, "address 1", -11.7545, -30.5697, Instant.now(), OrderStatus.PENDING);
        Order o2 = new Order(2L, "address 2", -11.7545, -30.5697, Instant.now(), OrderStatus.DELIVERED);
        Order o3 = new Order(3L, "address 3", -11.7545, -30.5697, Instant.now(), OrderStatus.PENDING);

        return Arrays.asList(new OrderDTO(o1), new OrderDTO(o2), new OrderDTO(o3));
    }

    public static List<Product> getProducts(){
        Product p1 = new Product(1L, "product 1", 7.0,"description", "image URI");
        Product p2 = new Product(2L, "product 2", 2.55,"description", "image URI");

        return Arrays.asList(p1, p2);
    }

    public static List<ProductDTO> getProductsDTO(){
        Product p1 = new Product(1L, "product 1", 7.0,"description", "image URI");
        Product p2 = new Product(2L, "product 2", 2.55,"description", "image URI");

        return Arrays.asList(new ProductDTO(p1), new ProductDTO(p2));
    }
}
