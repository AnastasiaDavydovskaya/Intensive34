package ru.aston.davydovskaya_av.task4.services;

import lombok.AllArgsConstructor;
import ru.aston.davydovskaya_av.task4.entities.User;
import ru.aston.davydovskaya_av.task4.repositories.impl.UserRepositoryImpl;

import java.util.List;

@AllArgsConstructor
public class UserService {

    private UserRepositoryImpl userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id);
    }

    public boolean deleteById(Long id){
        return userRepository.deleteById(id);
    }

    public boolean create(User user) {
        return userRepository.create(user);
    }

    public User update(User user) {
        return userRepository.update(user);
    }

    public List<String> getUsersWithOrders(){
        return userRepository.getUsersWithOrders();
    }
}
