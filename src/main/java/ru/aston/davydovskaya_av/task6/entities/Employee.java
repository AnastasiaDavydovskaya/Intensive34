package ru.aston.davydovskaya_av.task6.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.aston.davydovskaya_av.task6.enums.City;

@Data
@AllArgsConstructor
public class Employee {

    private Long id;
    private String firstName;
    private String lastName;
    private City city;
}
