package ru.aston.davydovskaya_av.task1.helpers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.aston.davydovskaya_av.task1.entities.trips.Trip;
import ru.aston.davydovskaya_av.task1.interfaces.Discountable;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@AllArgsConstructor
public final class TripHelper {

    private List<Trip> listTrips;

    public Integer getSumOfAllTripsWithoutDiscount() {
        return listTrips.stream()
                .map(Trip::getSum)
                .mapToInt(BigDecimal::intValue)
                .sum();
    }

    public Integer getSumOfAllTripsWithDiscount() {
        return listTrips.stream()
                .map(Discountable::getDiscount)
                .mapToInt(BigDecimal::intValue)
                .sum();
    }

    public List<Trip> getSortTripsBySurname() {
        return listTrips.stream().sorted(Comparator.comparing(o -> o.getUser().getSurname()))
                        .collect(Collectors.toList());
    }
}
