package com.practice.evaluation.products.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.evaluation.products.dto.details.ProductsDetailsDto;
import com.practice.evaluation.products.dto.response.ProductsResponseDto;
import com.practice.evaluation.products.model.ProductsRequest;
import com.practice.evaluation.products.services.ProductsServices;
import com.practice.evaluation.products.util.literal.Constants;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigInteger;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductsController.class)
public class ProductsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    @MockBean
    private ProductsServices productsServices;

    @Test
    void shouldConsultProducts() throws Exception {
        when(productsServices.handlerConsultProduct(Mockito.anyString())).thenReturn(
                List.of(ProductsResponseDto.builder()
                                .id(BigInteger.valueOf(9))
                        .build())
        );

        mockMvc.perform(get("/api/v1/productos/consultar?producto=9"))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void shouldGiveExceptionStatus405PersistProducts() throws Exception {
        when(productsServices.handlerPersistProduct(Mockito.any(ProductsRequest.class))).thenReturn(Constants.MESSAGE_PRODUCT_OK);

        mockMvc.perform(get("/api/v1/productos/guardar"))
                .andExpect(status().isMethodNotAllowed()).andReturn();
    }

    @Test
    void shouldGiveExceptionStatus415PersistProducts() throws Exception {
        when(productsServices.handlerPersistProduct(Mockito.any(ProductsRequest.class))).thenReturn(Constants.MESSAGE_PRODUCT_OK);

        var object = new ObjectMapper();
        var body = object.writeValueAsString(ProductsRequest.builder().build());

        mockMvc.perform(post("/api/v1/productos/guardar")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().is(400)).andReturn();
    }

    @Test
    void shouldPersistProducts() throws Exception {
        when(productsServices.handlerPersistProduct(Mockito.any(ProductsRequest.class))).thenReturn(Constants.MESSAGE_PRODUCT_OK);

        var object = new ObjectMapper();
        var body = object.writeValueAsString(ProductsRequest.builder()
                        .model("Modelo A")
                        .year("2024")
                        .ram("16GB")
                        .productsDetailsDtoList(List.of(
                                ProductsDetailsDto.builder()
                                        .gpu("3050TI")
                                        .storage("512GB")
                                        .build()
                        ))
                .build());

        mockMvc.perform(post("/api/v1/productos/guardar")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().is(201)).andReturn();
    }
}
