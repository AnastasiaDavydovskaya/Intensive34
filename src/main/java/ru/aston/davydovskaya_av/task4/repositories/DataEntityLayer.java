package ru.aston.davydovskaya_av.task4.repositories;

import java.util.List;

public interface DataEntityLayer<T> {

    List<T> findAll();
    T findById(Long id);
    boolean deleteById(Long id);
    boolean create(T t);
    T update(T t);
}
