package com.pescaria.dsdelivery.services;

import com.pescaria.dsdelivery.dto.OrderDTO;
import com.pescaria.dsdelivery.entities.Order;
import com.pescaria.dsdelivery.entities.OrderStatus;
import com.pescaria.dsdelivery.repositories.OrderRepository;
import com.pescaria.dsdelivery.repositories.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.pescaria.dsdelivery.utils.Util;

import java.time.Instant;
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
        List<Order> orders = Util.getOrders();
        Mockito.when(repository.findAllPendingOrderByMomentAsc()).thenReturn(orders);

        List ordersDTO = service.findAll();

        Assertions.assertEquals(3, ordersDTO.size());
        ordersDTO.forEach(order -> Assertions.assertEquals(OrderDTO.class, order.getClass()));
    }

    // Method: insert
    @Test
    public void shouldInsertOrderAndReturnOrderDTO(){
        Order obj = new Order(null, "address",  -14.8648,  -40.8369, null, null);
        obj.setProducts(Util.getProducts());

        Order objSaved = new Order(1L, "address",  -14.8648,  -40.8369, Instant.now(), OrderStatus.PENDING);
        objSaved.setProducts(obj.getProducts());

        Mockito.when(prodRepository.getOne(1L)).thenReturn(obj.getProducts().get(0));
        Mockito.when(repository.save(obj)).thenReturn(objSaved);

        OrderDTO objDTO = new OrderDTO(obj);
        OrderDTO insertedObj = service.insert(objDTO);

        Assertions.assertNotNull(obj.getProducts());
        Assertions.assertEquals(objSaved.getProducts().size(), insertedObj.getProducts().size());
        Assertions.assertEquals(objSaved.getId(), insertedObj.getId());
        Assertions.assertEquals(objSaved.getStatus(), OrderStatus.PENDING);
        Assertions.assertNotNull(objSaved.getMoment());
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
}
