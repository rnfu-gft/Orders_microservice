package com.ordersmicroservice.orders_microservice;

import com.ordersmicroservice.orders_microservice.models.Address;
import com.ordersmicroservice.orders_microservice.models.Order;
import com.ordersmicroservice.orders_microservice.models.OrderedProduct;
import lombok.Generated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ordersmicroservice.orders_microservice.dto.Status.PAID;
import static com.ordersmicroservice.orders_microservice.dto.Status.UNPAID;

@Generated
public class Datos {

    static List<OrderedProduct> productList = new ArrayList<>();


    public static Optional<Order> crearOrder001(){

        return Optional.of(new Order(1L,1L,"Valencia",PAID, "2001-21-21","2002-21-21", productList));
    }
    public static Optional<Order> crearOrder002() {
        return Optional.of(new Order(2L, 2L, "Barcelona", UNPAID, "2001-21-21","2002-21-21", productList));
    }

    public static Optional<Address> crearAddress001() {
        return Optional.of(new Address(1L, 1L, "C/ Colon", 10, "5A", "Valencia", "46001"));
    }
    public static Optional<Address> crearAddress002() {
        return Optional.of(new Address(2L, 2L, "C/ de Navarra", 8, "1B", "Barcelona", "10000"));
    }
    public static void main(String[] args) {
        productList.add(new OrderedProduct());
        productList.add(new OrderedProduct());
        productList.add(new OrderedProduct());
    }
}
