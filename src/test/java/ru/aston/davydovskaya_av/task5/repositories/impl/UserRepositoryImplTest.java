package ru.aston.davydovskaya_av.task5.repositories.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aston.davydovskaya_av.task4.entities.User;
import ru.aston.davydovskaya_av.task4.repositories.impl.UserRepositoryImpl;
import ru.aston.davydovskaya_av.task4.services.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryImplTest {

    @Mock
    private UserRepositoryImpl userRepository;
    @InjectMocks
    private UserService userService;
    private User user;

    @BeforeEach
    public void init() {
        user = User.builder()
                .id(1L)
                .firstName("Nastya")
                .lastName("Davydov")
                .patronymic("Vrovna")
                .phoneNumber("80296421224")
                .email("n@mail.ru")
                .orderId(1L)
                .build();
    }

    @Test
    void findAllTest() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        assertEquals(user, userService.findAll().get(0));
    }

    @Test
    void findByIdTest() {
        when(userRepository.findById(1L)).thenReturn(user);
        assertEquals(user, userService.findById(1L));
    }

    @Test
    void createTest() {
        when(userRepository.create(user)).thenReturn(true);
        assertTrue(userService.create(user));
    }

    @Test
    void updateTest() {
        User userNew = User.builder()
                .id(1L)
                .firstName("Nastya")
                .lastName("Davydovskaya")
                .patronymic("Vladimirovna")
                .phoneNumber("80296421224")
                .email("n@mail.ru")
                .orderId(1L)
                .build();
        when(userRepository.update(user)).thenReturn(userNew);
        assertEquals(userNew, userService.update(user));
    }

    @Test
    void deleteByIdTest() {
        when(userRepository.deleteById(1L)).thenReturn(true);
        assertTrue(userService.deleteById(1L));
    }

    @Test
    void getUsersWithOrdersTest(){
        List<String> list = new ArrayList<>();
        list.add("Пользователь: " + "Nastya"
                + " Количество товаров: " + "2"
                + " Начальная цена: " + "130.2");
        when(userRepository.getUsersWithOrders()).thenReturn(list);
        assertEquals(list.get(0), userService.getUsersWithOrders().get(0));
    }
}
