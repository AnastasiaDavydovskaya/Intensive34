package ru.aston.davydovskaya_av.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Тесты методов класса ArrayList
public class ArrayListTest {

    private List<String> arrayList;

    @BeforeEach
    public void init() {
        arrayList = new ArrayList<>();
        arrayList.add("Anastasiya");
        arrayList.add("Katerina");
        arrayList.add("Olga");
    }

    @Test
    void subListTest() {
        List<String> list = new ArrayList<>(Arrays.asList("Anastasiya", "Katerina"));

        assertEquals(list, arrayList.subList(0, 2));
    }

    @Test
    void containsAllTest() {
        List<String> list = new ArrayList<>(3);
        list.add("Olga");
        list.add("Anastasiya");
        list.add("Katerina");

        assertTrue(arrayList.containsAll(list));
    }

    @Test
    void addElementTest() {
        int size = arrayList.size();
        arrayList.add("John");

        assertEquals(size + 1, arrayList.size());
    }

    @Test
    void addElementAtParticularPositionTest() {
        arrayList.add(0, "John");

        assertEquals("John", arrayList.get(0));
    }

    @Test
    void addElementAtParticularPositionThrowsExceptionTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(3));
    }

    @Test
    void removeElementTest() {
        arrayList.remove(0);

        assertEquals("Katerina", arrayList.get(0));
    }

    @Test
    void removeElementThrowsExceptionTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(3));
    }

    @Test
    void clearTest() {
        arrayList.clear();

        assertEquals(0, arrayList.size());
    }
}
