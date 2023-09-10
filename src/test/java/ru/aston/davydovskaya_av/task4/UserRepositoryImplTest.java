package ru.aston.davydovskaya_av.task4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aston.davydovskaya_av.task4.entities.User;
import ru.aston.davydovskaya_av.task4.repositories.impl.UserRepositoryImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserRepositoryImplTest {

    private UserRepositoryImpl userRepository;
    private User userFirst;

    @BeforeEach
    void init() {
        userRepository = new UserRepositoryImpl();
        userFirst = User.builder()
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
        assertEquals(List.of(userFirst),
                userRepository.findAll());
    }

    @Test
    void findByIdTest() {
        assertEquals(userFirst, userRepository.findById(1L));
    }

    @Test
    void createTest() {
        User userSecond = User.builder()
                .id(2L)
                .firstName("Test")
                .lastName("Testov")
                .patronymic("Testovich")
                .phoneNumber("80291621384")
                .email("test@mail.ru")
                .orderId(1L)
                .build();

        assertTrue(userRepository.create(userSecond));
    }

    @Test
    void updateTest() {
        User userThird = User.builder()
                .id(2L)
                .firstName("TestNew")
                .lastName("TestovNew")
                .patronymic("TestovichNew")
                .phoneNumber("80448651081")
                .email("testNew@mail.ru")
                .orderId(1L)
                .build();

        assertEquals(userThird, userRepository.update(userThird));
    }

    @Test
    void deleteByIdTest() {
        assertTrue(userRepository.deleteById(2L));
    }

    @Test
    void getUsersWithOrdersTest(){
        assertEquals("Пользователь: Davydov Количество товаров: 1 Начальная цена: 120.4",
                userRepository.getUsersWithOrders().get(0));
    }
}
