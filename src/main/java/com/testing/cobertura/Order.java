package com.testing.cobertura;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Order {
    private String productId;
    private int quantity;
    private double price;
    private String customerId;

}
