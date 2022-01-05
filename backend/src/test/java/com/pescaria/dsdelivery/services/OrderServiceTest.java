package com.pescaria.dsdelivery.services;

import com.pescaria.dsdelivery.dto.OrderDTO;
import com.pescaria.dsdelivery.entities.Order;
import com.pescaria.dsdelivery.entities.OrderStatus;
import com.pescaria.dsdelivery.entities.Product;
import com.pescaria.dsdelivery.repositories.OrderRepository;
import com.pescaria.dsdelivery.repositories.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    private OrderService service;
    @Mock
    private OrderRepository repository;
    @Mock
    private ProductRepository prodRepository;

    @Before
    public void setup(){
        service = new OrderService(repository, prodRepository);
    }

    // Method: findAll
    @Test
    public void shouldBeListOrderDTO(){
        List<Order> orders = findAll();
        Mockito.when(repository.findAllPendingOrderByMomentAsc()).thenReturn(orders);

        List ordersDTO = service.findAll();

        Assertions.assertEquals(3, ordersDTO.size());
        ordersDTO.forEach(order -> Assertions.assertEquals(OrderDTO.class, order.getClass()));
    }

    // Method: insert
    @Test
    public void shouldInsertOrderAndReturnOrderDTO(){
        Order obj = new Order(null, "address",  -14.8648,  -40.8369, null, null);
        obj.setProducts(getProducts());

        Order objSaved = new Order(1L, "address",  -14.8648,  -40.8369, null, null);
        objSaved.setProducts(obj.getProducts());

        Mockito.when(prodRepository.getOne(1L)).thenReturn(obj.getProducts().get(0));
        Mockito.when(repository.save(obj)).thenReturn(objSaved);

        OrderDTO objDTO = new OrderDTO(obj);
        OrderDTO insertedObj = service.insert(objDTO);

        Assertions.assertNotNull(obj.getProducts());
        Assertions.assertEquals(objDTO.getProducts().size(), insertedObj.getProducts().size());
        Assertions.assertEquals(objSaved.getId(), insertedObj.getId());
    }


    // Method: setDelivered
    @Test
    public void shouldReturnAnOrderDTOWithStatusDelivered(){
        Order obj = new Order(null, "address",  -14.8648,  -40.8369, Instant.now(), OrderStatus.PENDING);
        obj.setId(1L);

        Mockito.when(repository.getOne(obj.getId()))
                .thenReturn(obj);
        Mockito.when(repository.save(obj))
                .thenReturn(obj);

        OrderDTO objDTO = service.setDelivered(1L);
        Assertions.assertEquals(OrderStatus.DELIVERED, objDTO.getStatus());
    }


    // Auxiliary methods
    private List<Order> findAll(){
        Order o1 = new Order(1L, "adress 1", -11.7545, -30.5697, Instant.now(), OrderStatus.PENDING);
        Order o2 = new Order(2L, "adress 2", -11.7545, -30.5697, Instant.now(), OrderStatus.DELIVERED);
        Order o3 = new Order(3L, "adress 3", -11.7545, -30.5697, Instant.now(), OrderStatus.PENDING);

        return Arrays.asList(o1, o2, o3);
    }

    private List<Product> getProducts(){
        Product p1 = new Product(1L, "product 1", 7.0,"description", "image URI");
        Product p2 = new Product(2L, "product 2", 2.55,"description", "image URI");

        return Arrays.asList(p1, p2);
    }
}
