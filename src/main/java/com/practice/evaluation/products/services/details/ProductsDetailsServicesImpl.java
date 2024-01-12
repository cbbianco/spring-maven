package com.practice.evaluation.products.services.details;

import com.practice.evaluation.products.entitiy.ProductsDetailsEntity;
import com.practice.evaluation.products.model.ProductsRequest;
import com.practice.evaluation.products.repository.ProductsDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @apiNote ProductsDetailsServicesImpl, Servicio encargado de gestionar a los detalles asociados a los productos
 *
 * @version 1.0.0
 */
@Service("ProductsDetailsServicesImpl")
@RequiredArgsConstructor
@Slf4j
public class ProductsDetailsServicesImpl implements ProductsDetailsServices{


    /**
     * @apiNote  Gestiona las operaciones asociadas a los detalles de los productos, productsDetailsRepository de tipo {@link ProductsDetailsRepository}
     */
    private final ProductsDetailsRepository productsDetailsRepository;

    /**
     * @see ProductsDetailsServices#handlerPersistProductDetails(ProductsRequest, BigInteger)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean handlerPersistProductDetails(ProductsRequest productsRequest, BigInteger idProduct) {
        log.info("ProductsServicesImpl@handlerPersistProductDetails");
        List<ProductsDetailsEntity> detailsList = new ArrayList<>();
        productsRequest.getProductsDetailsDtoList().forEach(e -> {
            detailsList.add(ProductsDetailsEntity.builder()
                    .gpu(e.getGpu())
                    .idProduct(idProduct)
                    .storage(e.getStorage())
                    .build());
        });

        var details = productsDetailsRepository.saveAll(detailsList);
        log.info("Detalles Insertados: {}", details);
        return !details.isEmpty();
    }
}
