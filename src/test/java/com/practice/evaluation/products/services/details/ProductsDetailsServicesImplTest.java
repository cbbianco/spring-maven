package com.practice.evaluation.products.services.details;

import com.practice.evaluation.products.dto.details.ProductsDetailsDto;
import com.practice.evaluation.products.entity.ProductsDetailsEntity;
import com.practice.evaluation.products.model.ProductsRequest;
import com.practice.evaluation.products.repository.ProductsDetailsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigInteger;
import java.util.List;

@SpringBootTest(classes = {ProductsDetailsServicesImpl.class, ProductsDetailsRepository.class})
public class ProductsDetailsServicesImplTest {

    @Autowired
    @MockBean
    private ProductsDetailsRepository productsDetailsRepository;

    @Autowired
    private ProductsDetailsServicesImpl productsDetailsServicesImpl;

    @Test
    @DisplayName("should give can persist at details associated")
    void shouldGivePersistProductsAndDetails() {

        var productRequest = ProductsRequest.builder()
                .ram("16GB")
                .model("Laptop A")
                .year("2024")
                .productsDetailsDtoList(List.of(ProductsDetailsDto.builder()
                        .gpu("3050TI")
                        .storage("512GB")
                        .build()))
                .build();

        Mockito.when(productsDetailsRepository.saveAll(Mockito.anyList())).thenReturn(List.of(
                ProductsDetailsEntity.builder()
                        .id(BigInteger.ONE)
                        .idProduct(BigInteger.ONE)
                        .storage("512GB")
                        .gpu("3050TI")
                        .build()
        ));

        Assertions.assertNotNull(productsDetailsServicesImpl.handlerPersistProductDetails(productRequest, BigInteger.ONE));
    }

    @Test
    @DisplayName("shouldn't give can persist at details associated")
    void shouldNtGivePersistProductsAndDetails() {

        var productRequest = ProductsRequest.builder()
                .ram("16GB")
                .model("Laptop A")
                .year("2024")
                .productsDetailsDtoList(List.of(ProductsDetailsDto.builder()
                        .gpu("3050TI")
                        .storage("512GB")
                        .build()))
                .build();

        Mockito.when(productsDetailsRepository.saveAll(Mockito.anyList())).thenReturn(List.of());

        Assertions.assertNotNull(productsDetailsServicesImpl.handlerPersistProductDetails(productRequest, BigInteger.ONE));
    }

    @Test
    @DisplayName("shouldn't give can update at details associated by not found the product principal")
    void shouldNtGiveUpdateByDetailsAssociatedByNotFoundTheProductPrincipal() {

        var productRequest = ProductsRequest.builder()
                .ram("16GB")
                .model("Laptop A")
                .year("2024")
                .productsDetailsDtoList(List.of(ProductsDetailsDto.builder()
                        .gpu("3050TI")
                        .storage("512GB")
                        .build()))
                .build();

        Mockito.when(productsDetailsRepository.findAllById(Mockito.anyList())).thenReturn(List.of());

        Assertions.assertNotNull(productsDetailsServicesImpl.handlerUpdateProductDetails(productRequest, BigInteger.ONE));
    }

    @Test
    @DisplayName("shouldn't give can update at details associated by not found the product principal")
    void shouldGiveUpdateByDetailsAssociated() {

        var productRequest = ProductsRequest.builder()
                .ram("16GB")
                .model("Laptop A")
                .year("2024")
                .productsDetailsDtoList(List.of(ProductsDetailsDto.builder()
                        .gpu("3050TI")
                        .storage("512GB")
                        .build()))
                .build();

        var entityDetails = List.of(
                ProductsDetailsEntity.builder()
                        .id(BigInteger.ONE)
                        .gpu("3050TI")
                        .storage("512GB")
                        .build()
        );

        Mockito.when(productsDetailsRepository.findAllById(Mockito.anyList())).thenReturn(entityDetails);

        Mockito.when(productsDetailsRepository.saveAll(Mockito.anyList())).thenReturn(entityDetails);

        Assertions.assertNotNull(productsDetailsServicesImpl.handlerUpdateProductDetails(productRequest, BigInteger.ONE));
    }
}
