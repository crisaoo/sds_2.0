package com.pescaria.dsdelivery.services;

import com.pescaria.dsdelivery.dto.ProductDTO;
import com.pescaria.dsdelivery.repositories.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    private ProductService service;
    @Mock
    private ProductRepository repository;

    @Before
    public void setup(){
        service = new ProductService(repository);
    }

    // Method: findAll
    @Test
    public void shouldBeListProductsDTO(){
        List products = service.findAll();
        Assertions.assertNotNull(products);
        products.forEach(obj -> Assertions.assertEquals(obj.getClass(), ProductDTO.class));
    }
}
