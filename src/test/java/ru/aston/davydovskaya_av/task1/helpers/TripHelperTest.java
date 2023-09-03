package ru.aston.davydovskaya_av.task1.helpers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aston.davydovskaya_av.task1.entities.User;
import ru.aston.davydovskaya_av.task1.entities.trips.impl.AviaTrip;
import ru.aston.davydovskaya_av.task1.entities.trips.impl.BusTrip;
import ru.aston.davydovskaya_av.task1.enums.AviaType;
import ru.aston.davydovskaya_av.task1.enums.BusType;
import ru.aston.davydovskaya_av.task1.exceptions.DiscountException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TripHelperTest {

    private TripHelper tripUtils;

    @BeforeEach
    void init(){
        AviaTrip aviaTripFirst = AviaTrip.builder()
                .id(1L)
                .sum(BigDecimal.valueOf(1000))
                .user(new User(1L, 24, "Masha", "Lombas"))
                .aviaType(AviaType.ECONOMY)
                .build();

        AviaTrip aviaTripSecond = AviaTrip.builder()
                .id(2L)
                .sum(BigDecimal.valueOf(4000))
                .user(new User(2L, 21, "Nastya", "Davydovskaya"))
                .aviaType(AviaType.BUSINESS)
                .build();

        BusTrip busTrip = BusTrip.builder()
                .id(3L)
                .sum(BigDecimal.valueOf(1200))
                .user(new User(3L, 26, "Olga", "Adaha"))
                .busType(BusType.ONE_STORY)
                .build();

        tripUtils = new TripHelper(Arrays.asList(aviaTripFirst, aviaTripSecond, busTrip));
    }

    @Test
    void getSumOfAllTripsWithoutDiscountTest() {
        assertEquals(tripUtils.getSumOfAllTripsWithoutDiscount(), 6200);
    }

    @Test
    void getSumOfAviaTripsWithDiscountThrowsDiscountExceptionTest() {
        AviaTrip aviaTripThird = AviaTrip.builder()
                .sum(BigDecimal.valueOf(900))
                .build();

        TripHelper tripHelper = new TripHelper(Collections.singletonList(aviaTripThird));
        assertThrows(DiscountException.class, tripHelper::getSumOfAllTripsWithDiscount);
    }

    @Test
    void getSumOfBusTripsWithDiscountThrowsDiscountExceptionTest() {
        BusTrip aviaTripThird = BusTrip.builder()
                .sum(BigDecimal.valueOf(700))
                .build();

        TripHelper tripHelper = new TripHelper(Collections.singletonList(aviaTripThird));
        assertThrows(DiscountException.class, tripHelper::getSumOfAllTripsWithDiscount);
    }

    @Test
    void getSumOfAllTripsWithDiscountTest() {
        assertEquals(tripUtils.getSumOfAllTripsWithDiscount(), 5720);
    }

    @Test
    void getSortTripsBySurnameTest() {
        assertEquals(tripUtils.getSortTripsBySurname().get(0).getUser().getSurname(), "Adaha");
        assertEquals(tripUtils.getSortTripsBySurname().get(1).getUser().getSurname(), "Davydovskaya");
        assertEquals(tripUtils.getSortTripsBySurname().get(2).getUser().getSurname(), "Lombas");
    }
}
