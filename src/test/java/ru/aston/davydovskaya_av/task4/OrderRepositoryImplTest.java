package ru.aston.davydovskaya_av.task4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aston.davydovskaya_av.task4.entities.Order;
import ru.aston.davydovskaya_av.task4.repositories.impl.OrderRepositoryImpl;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderRepositoryImplTest {

    private OrderRepositoryImpl orderRepository;
    private Order orderFirst;

    @BeforeEach
    void init() {
        orderRepository = new OrderRepositoryImpl();
        orderFirst = Order.builder()
                .id(1L)
                .amount(1)
                .unitPrice(BigDecimal.valueOf(120.4))
                .build();
    }

    @Test
    void findAllTest() {
        assertEquals(List.of(orderFirst), orderRepository.findAll());
    }

    @Test
    void findByIdTest() {
        assertEquals(orderFirst, orderRepository.findById(1L));
    }

    @Test
    void createTest() {
        Order orderSecond = Order.builder()
                .id(2L)
                .amount(1)
                .unitPrice(BigDecimal.valueOf(230.5))
                .build();

        assertTrue(orderRepository.create(orderSecond));
    }

    @Test
    void updateTest() {
        Order orderThird = Order.builder()
                .id(2L)
                .amount(4)
                .unitPrice(BigDecimal.valueOf(233.5))
                .build();

        assertEquals(orderThird, orderRepository.update(orderThird));
    }

    @Test
    void deleteByIdTest() {
        assertTrue(orderRepository.deleteById(2L));
    }
}
