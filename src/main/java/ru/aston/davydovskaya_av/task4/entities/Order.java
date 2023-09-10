package ru.aston.davydovskaya_av.task4.entities;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Order {

    private Long id;
    private Integer amount;
    private BigDecimal unitPrice;
}
