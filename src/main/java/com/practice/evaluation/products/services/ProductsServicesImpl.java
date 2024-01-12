package com.practice.evaluation.products.services;

import com.practice.evaluation.products.commons.exception.ProductException;
import com.practice.evaluation.products.entitiy.ProductsEntity;
import com.practice.evaluation.products.model.ProductsRequest;
import com.practice.evaluation.products.repository.ProductsRepository;
import com.practice.evaluation.products.services.details.ProductsDetailsServices;
import com.practice.evaluation.products.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

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
     * @see ProductsServices#handlerUpdateProduct()
     */
    @Override
    public String handlerUpdateProduct() {
        return null;
    }
}
