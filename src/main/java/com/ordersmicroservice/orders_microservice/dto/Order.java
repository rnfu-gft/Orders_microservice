package com.ordersmicroservice.orders_microservice.dto;

import com.ordersmicroservice.orders_microservice.models.OrderEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
<<<<<<< HEAD
//@Entity(name = "orders")
=======
>>>>>>> 6d8f867b809555a8edbf4a51befffbc7561fbca6
public class Order {

    @Id
    private Long id;
    private Long user_id;
    private String from_address;
    private String to_address;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String date_ordered;
    private String date_delivered;
}