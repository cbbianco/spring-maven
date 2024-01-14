package com.practice.evaluation.products.services;

import com.practice.evaluation.products.commons.exception.ProductException;
import com.practice.evaluation.products.dto.response.ProductsResponseDto;
import com.practice.evaluation.products.entity.ProductsEntity;
import com.practice.evaluation.products.model.ProductsRequest;
import com.practice.evaluation.products.repository.ProductsListRepository;
import com.practice.evaluation.products.repository.ProductsRepository;
import com.practice.evaluation.products.services.cache.ProductsCacheServices;
import com.practice.evaluation.products.services.details.ProductsDetailsServices;
import com.practice.evaluation.products.util.callhttp.NotifiedServices;
import com.practice.evaluation.products.util.literal.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.*;

/**
 * @apiNote ProductsServicesImpl, Servicio encargado de gestionar a los productos asociados
 *
 * @version 1.0.0
 */
@Service("ProductsServicesImpl")
@RequiredArgsConstructor
@Slf4j
public class ProductsServicesImpl implements ProductsServices{

    /**
     * @apiNote  Gestiona las operaciones asociadas a los productos, productsRepository de tipo {@link ProductsRepository}
     */
    private final ProductsRepository productsRepository;

    /**
     * @apiNote  Gestiona las operaciones asociadas al detalle de los productos, productsDetailsServices de tipo {@link ProductsDetailsServices}
     */
    private final ProductsDetailsServices productsDetailsServices;

    /**
     *
     */
    private final ProductsListRepository productsListRepository;

    /**
     * @apiNote Uri del servicio externo, uri de tipo {@link String}
     */
    @Value("${app.uriMock}")
    private String uri;

    /**
     * @apiNote  Gestiona las solicitudes externas, notifiedServices de tipo {@link NotifiedServices}
     */
    private final NotifiedServices<Object,ProductsResponseDto> notifiedServices;

    /**
     * @apiNote servicio de cache, productsCacheServices de tipo {@link ProductsCacheServices}
     */
    private final ProductsCacheServices productsCacheServices;

    /**
     * @see ProductsServices#handlerPersistProduct(ProductsRequest)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String handlerPersistProduct(ProductsRequest productsRequest) {

        try {
            log.info("ProductsServicesImpl@handlerPersistProduct");
            var finder = productsRepository.findByModel(productsRequest.getModel());
            if(Objects.isNull(finder)) {
                var product = Optional.of(productsRepository.save(ProductsEntity.builder()
                        .year(productsRequest.getYear())
                        .ram(productsRequest.getRam())
                        .model(productsRequest.getModel())
                        .build()));

                log.info("Producto Insertado: {}", product);
                return productsDetailsServices.handlerPersistProductDetails(productsRequest, product.get().getId())
                        ? Constants.MESSAGE_PRODUCT_OK
                        : Constants.MESSAGE_PRODUCT_ERROR;
            }

            throw new ProductException(Constants.MESSAGE_PRODUCT_ERROR_EXIST, HttpStatus.OK);
        }catch (Exception e) {
            if(e instanceof ProductException) {
                var exception = (ProductException)e;
                throw new ProductException(exception.getMessage(), exception.getStatus());
            }

            throw new RuntimeException("Error al persistir su producto", e);
        }
    }

    /**
     * @see ProductsServices#handlerUpdateProduct(ProductsRequest, String)
     */
    @Override
    public String handlerUpdateProduct(ProductsRequest productsRequest, String productId) {
        try {
            log.info("ProductsServicesImpl@handlerUpdateProduct");
            var finder = productsRepository.findById(BigInteger.valueOf(Long.parseLong(productId)));
            if(finder.isPresent()) {
                var product = Optional.of(productsRepository.save(ProductsEntity.builder()
                        .year(productsRequest.getYear())
                        .ram(productsRequest.getRam())
                        .model(productsRequest.getModel())
                        .id(finder.get().getId())
                        .build()));

                log.info("Producto Actualizado: {}", product);
                return productsDetailsServices.handlerUpdateProductDetails(productsRequest, product.get().getId())
                        ? Constants.MESSAGE_PRODUCT_UPDATE_OK
                        : Constants.MESSAGE_PRODUCT_UPDATE_ERROR;
            }

            throw new ProductException(Constants.MESSAGE_PRODUCT_ERROR_NOT_EXIST, HttpStatus.NO_CONTENT);
        }catch (Exception e) {
            if(e instanceof ProductException) {
                var exception = (ProductException)e;
                throw new ProductException(exception.getMessage(), exception.getStatus());
            }

            throw new RuntimeException("Error al actualizar su producto", e);
        }
    }

    /**
     * @see ProductsServices#handlerConsultProduct(String)
     */
    @Override
    public List<ProductsResponseDto> handlerConsultProduct(String productId) {

        try {
            log.info("ProductsServicesImpl@handlerConsultProduct");
            var responseExtern = this.notifiedServices.notifiedRequest(null, ProductsResponseDto.class, Map.of(), Map.of(), "GET", uri + "?producto=" + productId);
            var products = productsListRepository.findById(BigInteger.valueOf(Long.parseLong(productId)));
            log.info("Información Externa: {}", responseExtern);
            if(!Objects.isNull(responseExtern) && products.isPresent()) {
                log.info("Productos desde la BD: {}", products);
                responseExtern.setProductsListEntity(products.get());
            }

            var productsCache = this.productsCacheServices.handlerProductsCache();
            if(!productsCache.isEmpty()) {
                log.info("Productos obtenido del cache: {}", productsCache);
                responseExtern.setProductsCache(productsCache);
            }

            return List.of(responseExtern);
        }catch (Exception e) {
            throw new RuntimeException("Error al consultar su producto", e);
        }
    }
}
