package ru.aston.davydovskaya_av.task4.services;

import lombok.AllArgsConstructor;
import ru.aston.davydovskaya_av.task4.entities.Order;
import ru.aston.davydovskaya_av.task4.repositories.impl.OrderRepositoryImpl;

import java.util.List;

@AllArgsConstructor
public class OrderService {

    private OrderRepositoryImpl orderRepository;

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order findById(Long id){
        return orderRepository.findById(id);
    }

    public boolean deleteById(Long id){
        return orderRepository.deleteById(id);
    }

    public boolean create(Order order) {
        return orderRepository.create(order);
    }

    public Order update(Order order) {
        return orderRepository.update(order);
    }
}
