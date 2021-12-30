package com.pescaria.dsdelivery.repositories;

import com.pescaria.dsdelivery.entities.Order;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository repository;

    @Test
    public void shouldReturnAListOfPendingOrders(){
        List<Order> orders = repository.findAllPendingOrderByMomentAsc();
        for(Order order : orders)
            Assertions.assertEquals(0, order.getStatus().ordinal());
    }
}
