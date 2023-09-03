package ru.aston.davydovskaya_av.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

// Тесты методов класса HashMap
public class HashMapTest {

    private Map<String, String> map;

    @BeforeEach
    public void init() {
        map = new HashMap<>();
        map.put("keyFirst", "valueFirst");
        map.put("keySecond", "valueSecond");
        map.put("keyThird", "valueThird");
    }

    @Test
    void putAllTest() {
        Map<String, String> mapFirst = new HashMap<>(2);
        mapFirst.put("keyFourth", "valueFourth");
        Map<String, String> mapSecond = new HashMap<>(Map.of(
                "keyFirst", "valueFirst",
                "keySecond", "valueSecond",
                "keyThird", "valueThird",
                "keyFourth", "valueFourth"
        ));

        map.putAll(mapFirst);
        assertEquals(mapSecond, map);
    }

    @Test
    void putTest() {
        Map<String, String> mapFirst = new HashMap<>(4, 0.6F);
        mapFirst.put("keyFirst", "valueFirst");
        mapFirst.put("keySecond", "valueSecond");
        mapFirst.put("keyThird", "valueThird");
        mapFirst.put("keyFourth", "valueFourth");

        map.put("keyFourth", "valueFourth");
        assertEquals(mapFirst, map);
    }

    @Test
    void containsKeyTest() {
        assertTrue(map.containsKey("keyFirst"));
    }

    @Test
    void containsValueTest() {
        assertTrue(map.containsValue("valueSecond"));
    }

    @Test
    void isEmptyTest() {
        assertFalse(map.isEmpty());
    }
}
