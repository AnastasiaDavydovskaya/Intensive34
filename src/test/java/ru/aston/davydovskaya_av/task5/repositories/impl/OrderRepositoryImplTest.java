package ru.aston.davydovskaya_av.task5.repositories.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aston.davydovskaya_av.task4.entities.Order;
import ru.aston.davydovskaya_av.task4.repositories.impl.OrderRepositoryImpl;
import ru.aston.davydovskaya_av.task4.services.OrderService;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderRepositoryImplTest {

    @Mock
    private OrderRepositoryImpl orderRepository;
    @InjectMocks
    private OrderService orderService;
    private Order order;

    @BeforeEach
    public void init() {
        order = Order.builder()
                .id(1L)
                .amount(2)
                .unitPrice(BigDecimal.valueOf(233.5))
                .build();
    }

    @Test
    void findAllTest() {
        when(orderRepository.findAll()).thenReturn(List.of(order));
        assertEquals(order, orderService.findAll().get(0));
    }

    @Test
    void findByIdTest() {
        when(orderRepository.findById(1L)).thenReturn(order);
        assertEquals(order, orderService.findById(1L));
    }

    @Test
    void createTest() {
        when(orderRepository.create(order)).thenReturn(true);
        assertTrue(orderService.create(order));
    }

    @Test
    void updateTest() {
        Order orderNew = Order.builder()
                .id(1L)
                .amount(1)
                .unitPrice(BigDecimal.valueOf(100.8))
                .build();
        when(orderRepository.update(order)).thenReturn(orderNew);
        assertEquals(orderNew, orderService.update(order));
    }

    @Test
    void deleteByIdTest() {
        when(orderRepository.deleteById(1L)).thenReturn(true);
        assertTrue(orderService.deleteById(1L));
    }
}
