package ru.aston.davydovskaya_av.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Тесты методов класса TreeSet
public class TreeSetTest {

    private TreeSet<String> set;

    @BeforeEach
    public void init() {
        set = new TreeSet<>();
        set.add("First");
        set.add("Second");
        set.add("Third");
    }

    @Test
    public void subSetTest() {
        SortedSet<String> sortedSetFirst = new TreeSet<>(Comparator.naturalOrder());
        sortedSetFirst.add("First");
        sortedSetFirst.add("Second");

        assertEquals(sortedSetFirst, set.subSet("First", "Third"));
    }

    @Test
    public void headSetTest() {
        SortedSet<String> sortedSetFirst = new TreeSet<>(Comparator.naturalOrder());
        Set<String> treeSetFirst = new TreeSet<>(sortedSetFirst);
        treeSetFirst.add("First");

        assertEquals(treeSetFirst, set.headSet("Second"));
    }

    @Test
    public void addAllTest() {
        Set<String> treeSetFirst = new TreeSet<>(Arrays.asList("First", "Second", "Third", "Fourth", "Fifth"));
        Set<String> treeSetSecond = new TreeSet<>(Arrays.asList("Fourth", "Fifth"));
        set.addAll(treeSetSecond);

        assertEquals(treeSetFirst, set);
    }

    @Test
    public void sortTest() {
        assertEquals("First", set.first());
    }
}
