package com.practice.evaluation.products.services;

import com.practice.evaluation.products.commons.exception.ProductException;
import com.practice.evaluation.products.dto.details.ProductsDetailsDto;
import com.practice.evaluation.products.dto.response.ProductsResponseDto;
import com.practice.evaluation.products.entity.ProductsEntity;
import com.practice.evaluation.products.model.ProductsRequest;
import com.practice.evaluation.products.repository.ProductsListRepository;
import com.practice.evaluation.products.repository.ProductsRepository;
import com.practice.evaluation.products.services.cache.ProductsCacheServices;
import com.practice.evaluation.products.services.details.ProductsDetailsServices;
import com.practice.evaluation.products.util.callhttp.NotifiedServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = {ProductsServicesImpl.class, ProductsDetailsServices.class, ProductsRepository.class, ProductsListRepository.class, NotifiedServices.class, ProductsCacheServices.class})
public class ProductsServicesImplTest {

    @Autowired
    @MockBean
    private ProductsDetailsServices productsDetailsServices;

    @Autowired
    @MockBean
    private ProductsRepository productsRepository;

    @Autowired
    @MockBean
    private ProductsListRepository productsListRepository;

    @Autowired
    @MockBean
    private ProductsCacheServices productsCacheServices;

    @Autowired
    @MockBean
    private NotifiedServices<Object, ProductsResponseDto> notifiedServices;

    @Autowired
    private ProductsServicesImpl productsServicesImpl;


    @Test
    @DisplayName("should give can persist at product and details associated")
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

        var entitySave =   ProductsEntity.builder()
                .year(productRequest.getYear())
                .ram(productRequest.getRam())
                .model(productRequest.getModel())
                .id(BigInteger.ONE)
                .build();

        Mockito.when(productsRepository.findByModel(productRequest.getModel())).thenReturn(null);

        Mockito.when(productsRepository.save(Mockito.any(ProductsEntity.class))).thenReturn(entitySave);

        Mockito.when(productsDetailsServices.handlerPersistProductDetails(Mockito.any(ProductsRequest.class),Mockito.any(BigInteger.class))).thenReturn(true);

        Assertions.assertNotNull(productsServicesImpl.handlerPersistProduct(productRequest));
    }

    @Test
    @DisplayName("shouldn't give can persist because exist")
    void shouldNtGivePersistProductsBecauseExist() {

        var productRequest = ProductsRequest.builder()
                .ram("16GB")
                .model("Laptop A")
                .year("2024")
                .productsDetailsDtoList(List.of(ProductsDetailsDto.builder()
                        .gpu("3050TI")
                        .storage("512GB")
                        .build()))
                .build();

        var entity =   ProductsEntity.builder()
                .year(productRequest.getYear())
                .ram(productRequest.getRam())
                .model(productRequest.getModel())
                .id(BigInteger.ONE)
                .build();

        Mockito.when(productsRepository.findByModel(productRequest.getModel())).thenReturn(entity);

        Assertions.assertThrows(ProductException.class, () -> productsServicesImpl.handlerPersistProduct(productRequest));
    }

    @Test
    @DisplayName("should give can persist at product")
    void shouldGivePersistProducts() {

        var productRequest = ProductsRequest.builder()
                .ram("16GB")
                .model("Laptop A")
                .year("2024")
                .productsDetailsDtoList(List.of(ProductsDetailsDto.builder()
                        .gpu("3050TI")
                        .storage("512GB")
                        .build()))
                .build();

        var entity =   ProductsEntity.builder()
                .year(productRequest.getYear())
                .ram(productRequest.getRam())
                .model(productRequest.getModel())
                .build();

        var entitySave =   ProductsEntity.builder()
                .year(productRequest.getYear())
                .ram(productRequest.getRam())
                .model(productRequest.getModel())
                .id(BigInteger.ONE)
                .build();

        Mockito.when(productsRepository.findByModel(productRequest.getModel())).thenReturn(null);

        Mockito.when(productsRepository.save(Mockito.any(ProductsEntity.class))).thenReturn(entitySave);

        Mockito.when(productsDetailsServices.handlerPersistProductDetails(productRequest,entity.getId())).thenReturn(false);

        Assertions.assertNotNull(productsServicesImpl.handlerPersistProduct(productRequest));
    }

    @Test
    @DisplayName("should give exception by persist at details")
    void shouldGiveExceptionPersistDetailsProducts() {

        var productRequest = ProductsRequest.builder()
                .ram("16GB")
                .model("Laptop A")
                .year("2024")
                .productsDetailsDtoList(List.of(ProductsDetailsDto.builder()
                        .gpu("3050TI")
                        .storage("512GB")
                        .build()))
                .build();

        var entitySave =   ProductsEntity.builder()
                .year(productRequest.getYear())
                .ram(productRequest.getRam())
                .model(productRequest.getModel())
                .id(BigInteger.ONE)
                .build();

        Mockito.when(productsRepository.findByModel(productRequest.getModel())).thenReturn(null);

        Mockito.when(productsRepository.save(Mockito.any(ProductsEntity.class))).thenReturn(entitySave);

        Mockito.when(productsDetailsServices.handlerPersistProductDetails(Mockito.any(ProductsRequest.class),Mockito.any(BigInteger.class))).thenThrow(NullPointerException.class);

        Assertions.assertThrows(RuntimeException.class, () -> productsServicesImpl.handlerPersistProduct(productRequest));
    }

    @Test
    @DisplayName("should give update at product and details associated")
    void shouldGiveUpdateAtProductAndDetails() {

        var productRequest = ProductsRequest.builder()
                .ram("16GB")
                .model("Laptop A")
                .year("2024")
                .productsDetailsDtoList(List.of(ProductsDetailsDto.builder()
                        .gpu("3050TI")
                        .storage("512GB")
                        .build()))
                .build();

        var entity =   ProductsEntity.builder()
                .year(productRequest.getYear())
                .ram(productRequest.getRam())
                .model(productRequest.getModel())
                .id(BigInteger.ONE)
                .build();

        var entitySave =   ProductsEntity.builder()
                .year(productRequest.getYear())
                .ram(productRequest.getRam())
                .model(productRequest.getModel())
                .id(BigInteger.ONE)
                .build();

        Mockito.when(productsRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.of(entity));

        Mockito.when(productsRepository.save(Mockito.any(ProductsEntity.class))).thenReturn(entitySave);

        Mockito.when(productsDetailsServices.handlerUpdateProductDetails(Mockito.any(ProductsRequest.class),Mockito.any(BigInteger.class))).thenReturn(true);

        Assertions.assertNotNull(productsServicesImpl.handlerUpdateProduct(productRequest, "1"));
    }

    @Test
    @DisplayName("should give update just product and not can the details associated")
    void shouldGiveUpdateAtJustProductAndNotDetails() {

        var productRequest = ProductsRequest.builder()
                .ram("16GB")
                .model("Laptop A")
                .year("2024")
                .productsDetailsDtoList(List.of(ProductsDetailsDto.builder()
                        .gpu("3050TI")
                        .storage("512GB")
                        .build()))
                .build();

        var entity =   ProductsEntity.builder()
                .year(productRequest.getYear())
                .ram(productRequest.getRam())
                .model(productRequest.getModel())
                .id(BigInteger.ONE)
                .build();

        var entitySave =   ProductsEntity.builder()
                .year(productRequest.getYear())
                .ram(productRequest.getRam())
                .model(productRequest.getModel())
                .id(BigInteger.ONE)
                .build();

        Mockito.when(productsRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.of(entity));

        Mockito.when(productsRepository.save(Mockito.any(ProductsEntity.class))).thenReturn(entitySave);

        Mockito.when(productsDetailsServices.handlerUpdateProductDetails(Mockito.any(ProductsRequest.class),Mockito.any(BigInteger.class))).thenReturn(false);

        Assertions.assertNotNull(productsServicesImpl.handlerUpdateProduct(productRequest, "1"));
    }

    @Test
    @DisplayName("shouldn't give update the product and details")
    void shouldGiveExceptionInUpdateProductAndDetails() {

        var productRequest = ProductsRequest.builder()
                .ram("16GB")
                .model("Laptop A")
                .year("2024")
                .productsDetailsDtoList(List.of(ProductsDetailsDto.builder()
                        .gpu("3050TI")
                        .storage("512GB")
                        .build()))
                .build();

        var entity =   ProductsEntity.builder()
                .year(productRequest.getYear())
                .ram(productRequest.getRam())
                .model(productRequest.getModel())
                .id(BigInteger.ONE)
                .build();

        var entitySave =   ProductsEntity.builder()
                .year(productRequest.getYear())
                .ram(productRequest.getRam())
                .model(productRequest.getModel())
                .id(BigInteger.ONE)
                .build();

        Mockito.when(productsRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.of(entity));

        Mockito.when(productsRepository.save(Mockito.any(ProductsEntity.class))).thenReturn(entitySave);

        Mockito.when(productsDetailsServices.handlerUpdateProductDetails(Mockito.any(ProductsRequest.class),Mockito.any(BigInteger.class))).thenThrow(NullPointerException.class);

        Assertions.assertThrows(RuntimeException.class, () -> productsServicesImpl.handlerUpdateProduct(productRequest, "1"));
    }

    @Test
    @DisplayName("shouldn't give update the product and details")
    void shouldGiveExceptionInUpdateProductAndDetailsByIdNotFound() {

        var productRequest = ProductsRequest.builder()
                .ram("16GB")
                .model("Laptop A")
                .year("2024")
                .productsDetailsDtoList(List.of(ProductsDetailsDto.builder()
                        .gpu("3050TI")
                        .storage("512GB")
                        .build()))
                .build();


        Mockito.when(productsRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.empty());

        Assertions.assertThrows(ProductException.class, () -> productsServicesImpl.handlerUpdateProduct(productRequest, "1"));
    }
}
