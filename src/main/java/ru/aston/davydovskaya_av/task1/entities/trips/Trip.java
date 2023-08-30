package ru.aston.davydovskaya_av.task1.entities.trips;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.aston.davydovskaya_av.task1.entities.User;
import ru.aston.davydovskaya_av.task1.interfaces.Discountable;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@ToString
@SuperBuilder
public abstract class Trip implements Discountable {

    protected Long id;
    private BigDecimal sum;
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return Objects.equals(id, trip.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
