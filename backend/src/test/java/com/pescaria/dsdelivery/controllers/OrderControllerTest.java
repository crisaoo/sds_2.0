package com.pescaria.dsdelivery.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pescaria.dsdelivery.dto.OrderDTO;
import com.pescaria.dsdelivery.entities.Order;
import com.pescaria.dsdelivery.services.OrderService;
import com.pescaria.dsdelivery.utils.Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {
    @MockBean
    private OrderService service;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;

    // Method: findAll
    @Test
    public void shouldReturnStatus200ToGetRequest() throws Exception {
        Mockito.when(service.findAll())
            .thenReturn(Util.getOrdersDTO());

        mvc.perform(get("/orders"))
            .andExpect(status().isOk());
    }

    // Method: insert
    @Test
    public void shouldReturnStatus201ToPostRequest() throws Exception{
        Order obj = new Order(null, "address", -11.7545, -30.5697, null, null);
        OrderDTO objDTO = new OrderDTO(obj);
        objDTO.setProducts(Util.getProductsDTO());

        Mockito.when(service.insert(Mockito.any(OrderDTO.class)))
           .thenReturn(objDTO);

        String objJson = mapper.writeValueAsString(objDTO);

        mvc.perform(
            post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objJson))
            .andExpect(status().isCreated()
        );
    }

    // Method: setDelivered
    @Test
    public void shouldReturnStatus200ToPutRequest() throws Exception{
        Mockito.when(service.setDelivered(1L))
            .thenReturn(new OrderDTO());

        mvc.perform(
            put("/orders/{id}/delivered", 1L))
            .andExpect(status().isOk()
        );
    }
}
