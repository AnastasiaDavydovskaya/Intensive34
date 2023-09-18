package ru.aston.davydovskaya_av.task6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.aston.davydovskaya_av.task6.entities.Employee;
import ru.aston.davydovskaya_av.task6.enums.City;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamAPITest {

    private Stream<Employee> employeeStream;

    @BeforeEach
    public void init() {
        employeeStream = Stream.of(
                new Employee(1L, "Nastya", "Davydovskaya", City.MINSK),
                new Employee(2L, "Lena", "Janova", City.MINSK),
                new Employee(3L, "Kolya", "Afdan", City.VITEBSK),
                new Employee(4L, "Alex", "Jagrw", City.GRODNO),
                new Employee(5L, "Olya", "Alefd", City.GOMEL),
                new Employee(6L, "Irina", "Kawka", City.BREST),
                new Employee(7L, "Katya", "Jawdan", City.GOMEL));
    }

    @Test
    @DisplayName("Собрать все уникальные элементы Stream в список и отсортировать их.")
    void getUniqueAndSortedElementsInStream() {
        List<Integer> list = Stream.of(1, 10, 3, 4, 3, 1)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        assertEquals(Arrays.asList(4, 10), list);
    }

    @Test
    @DisplayName("Разделить элементы Stream на две группы: четные и нечетные.")
    void getEvenAndOddGroupsTest() {
        Map<Boolean, List<Integer>> partitions = Stream.iterate(1, i -> i + 1)
                .limit(10)
                .collect(Collectors.partitioningBy(x -> x % 2 == 0));

        assertEquals(Arrays.asList(2, 4, 6, 8, 10), partitions.get(true));
        assertEquals(Arrays.asList(1, 3, 5, 7, 9), partitions.get(false));
    }

    @Test
    @DisplayName("Создать два стрима: один из чисел от 0 до 10, другой из чисел от 10 до 20. " +
            "Объединить их в один стрим и вывести на экран числа больше 10.")
    void getStreamUnionAndFilterTest() {
        Stream<Integer> firstStream = Stream.iterate(0, i -> i + 1).limit(11);
        Stream<Integer> secondStream = Stream.iterate(10, i -> i + 1).limit(11);

        Stream.concat(firstStream, secondStream).filter(number -> number > 10).forEach(System.out::println);
    }

    @Test
    @DisplayName("Создать три стрима из массивов чисел от 1 до 10, от 10 до 20 и от 20 до 30 соответственно. " +
            "Объединить их в один стрим и вывести числа, которые кратны 5.")
    void getJoinArraysTest() {
        Stream<Integer> integerStream1 = Stream.iterate(1, i -> i + 1).limit(10);
        Stream<Integer> integerStream2 = Stream.iterate(10, i -> i + 1).limit(11);
        Stream<Integer> integerStream3 = Stream.iterate(20, i -> i + 1).limit(11);

        Stream.of(integerStream1, integerStream2, integerStream3)
                .flatMap(integerStream -> integerStream)
                .filter(numbers -> numbers % 5 == 0)
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("Разделить пользователей в Stream на группы по городу, " +
            "посчитать количество пользователей в каждой группе.")
    void getCountingEmployeeOfEachGroupByCityTest() {
        Map<City, Long> numEmployeesByCity =
                employeeStream.collect(groupingBy(Employee::getCity, counting()));

        assertEquals(2, numEmployeesByCity.get(City.MINSK));
        assertEquals(1, numEmployeesByCity.get(City.VITEBSK));
        assertEquals(1, numEmployeesByCity.get(City.GRODNO));
        assertEquals(2, numEmployeesByCity.get(City.GOMEL));
        assertEquals(1, numEmployeesByCity.get(City.BREST));
    }

    @Test
    @DisplayName("Задано множество фамилий сотрудников. Разработать программу, которая отображает все фамилии, " +
            "начинающиеся на букву «J». Задачу решить с использованием Stream API.")
    void getJLastNamesTest() {
        List<Employee> list = employeeStream.filter(employee -> employee.getLastName().startsWith("J"))
                .toList();

        assertEquals(3, list.size());
    }

    @Test
    @DisplayName("Задан массив строк. Используя средства StreamAPI отсортировать строки в лексикографическом порядке.")
    void getSortedArrayTest() {
        List<String> list = employeeStream.map(Employee::getFirstName)
                .sorted()
                .toList();

        assertEquals(Arrays.asList("Alex", "Irina", "Katya", "Kolya", "Lena", "Nastya", "Olya"), list);
    }

    @Test
    @DisplayName("Соберите все элементы Stream в одну строку через пробел и выведите результат.")
    void getStringFromWordsTest() {
        String string = employeeStream.map(Employee::getFirstName)
                .collect(Collectors.joining(" "));
        assertEquals("Nastya Lena Kolya Alex Olya Irina Katya", string);
    }

    @Test
    @DisplayName("Соберите слова в Stream в один текст, где каждое слово начинается с большой буквы и выведите результат.")
    void getStringFromWordsWithUppercaseFirstLettersTest() {
        String string = Stream.of("couch", "java", "apple", "bottle")
                .map(str -> str.substring(0, 1).toUpperCase() + str.substring(1))
                .collect(Collectors.joining(" "));
        assertEquals("Couch Java Apple Bottle", string);
    }

    @Test
    @DisplayName("Соберите числа в Stream в одно число, перемножив их между собой и выведите результат.")
    void getMultiplicationOfNumbersTest() {
        Integer integer = Stream.of(1, 2, 3, 4, 5)
                .reduce((x, y) -> x * y)
                .get();
        assertEquals(120, integer);
    }
}
