package com.ordersmicroservice.orders_microservice.services.impl;

import com.ordersmicroservice.orders_microservice.exception.GlobalExceptionHandler;
import com.ordersmicroservice.orders_microservice.exception.NotFoundException;
import com.ordersmicroservice.orders_microservice.models.OrderedProduct;
import com.ordersmicroservice.orders_microservice.repositories.OrderedProductRepository;
import com.ordersmicroservice.orders_microservice.services.OrderedProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderedProductServiceImpl implements OrderedProductService {
    OrderedProductRepository orderedProductRepository;
    public OrderedProductServiceImpl(OrderedProductRepository orderedProductRepository){
        this.orderedProductRepository = orderedProductRepository;
    }
    @Override
    public List<OrderedProduct> getAllProductsFromOrder(Long orderId) {
        return Optional.of(orderedProductRepository.findAll()).filter(orderedProducts -> !orderedProducts.isEmpty())
                .orElseThrow(() -> new NotFoundException("No orders were found"));
    }
}
