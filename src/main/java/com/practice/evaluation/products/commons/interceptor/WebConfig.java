package com.practice.evaluation.products.commons.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  @apiNote WebConfig, Configurador Central del Interceptor
 *
 * @version 1.0.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * @apiNote customInterceptor, Instancia del Interceptor
     *
     * @return {@link RequestHandlerInterceptor}
     */
    @Bean
    public RequestHandlerInterceptor customInterceptor() {
        return new RequestHandlerInterceptor();
    }

    /**
     * @apiNote addInterceptors, Se añade el interceptor
     *
     * @param registry de tipo {@link InterceptorRegistry}
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customInterceptor());
    }
}
