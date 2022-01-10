package com.pescaria.dsdelivery.controllers;

import com.pescaria.dsdelivery.services.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.pescaria.dsdelivery.utils.Util;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @MockBean
    private ProductService service;
    @Autowired
    private MockMvc mvc;


    @Test
    public void shouldReturnStatus200ToGetRequest() throws Exception{
        Mockito.when(service.findAll()).thenReturn(Util.getProductsDTO());

        mvc.perform(get("/products"))
            .andExpect(status().isOk());
    }
}
