package ru.aston.davydovskaya_av.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Тесты методов класса Collections
public class CollectionsTest {

    private List<String> arrayList;

    @BeforeEach
    public void init() {
        arrayList = new ArrayList<>();
        arrayList.add("Anastasiya");
        arrayList.add("Katerina");
        arrayList.add("Olga");
    }

    @Test
    public void frequencyTest() {
        assertEquals(1, Collections.frequency(arrayList, "Olga"));
    }

    @Test
    public void reverseTest() {
        List<String> list = new ArrayList<>(Arrays.asList("Olga", "Katerina", "Anastasiya"));
        Collections.reverse(arrayList);

        assertEquals(list, arrayList);
    }

    @Test
    public void disjointTest() {
        List<String> list = new ArrayList<>(Arrays.asList("Roma", "Igor"));

        assertTrue(Collections.disjoint(arrayList, list));
    }

    @Test
    public void fillTest() {
        Collections.fill(arrayList, "Anna");
        assertEquals("Anna", arrayList.get(0));
    }

    @Test
    public void addAllTest() {
        List<String> list = new ArrayList<>(Arrays.asList("Anastasiya", "Katerina", "Olga", "Roma", "Igor"));
        Collections.addAll(arrayList, "Roma", "Igor");

        assertEquals(list, arrayList);
    }

    @Test
    public void replaceAllTest() {
        List<String> list = new ArrayList<>(Arrays.asList("Anastasiya", "Sasha", "Olga"));
        Collections.replaceAll(arrayList, "Katerina", "Sasha");

        assertEquals(list, arrayList);
    }
}
