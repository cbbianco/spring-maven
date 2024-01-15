package com.practice.evaluation.products.services.cache;

import com.practice.evaluation.products.entity.ProductsEntity;
import com.practice.evaluation.products.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @apiNote ProductsCacheServicesImpl, Servicio encargado de gestionar a los productos en el cache
 *
 * @version 1.0.0
 */
@Service("ProductsCacheServicesImpl")
@RequiredArgsConstructor
@Slf4j
public class ProductsCacheServicesImpl implements ProductsCacheServices{


    /**
     * @apiNote  Gestiona las operaciones asociadas a los productos, productsRepository de tipo {@link ProductsRepository}
     */
    private final ProductsRepository productsRepository;

    /**
     * @see ProductsCacheServices#handlerProductsCache()
     */
    @Override
    @Cacheable("EvaluationCache")
    public List<ProductsEntity> handlerProductsCache() {
        log.info("ProductsCacheServicesImpl@handlerProductsCache");
        var finder = this.productsRepository.findAll();
        log.info("Productos Encontrados: {}", finder.size());
        if(finder.size() >= 2) {
            return finder.subList(0,2);
        }

        return List.of();
    }
}
