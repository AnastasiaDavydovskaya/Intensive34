package ru.aston.davydovskaya_av.task1.entities.trips.impl;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import ru.aston.davydovskaya_av.task1.entities.trips.Trip;
import ru.aston.davydovskaya_av.task1.enums.AviaType;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
public class AviaTrip extends Trip {

    private AviaType aviaType;

    @Override
    public BigDecimal getDiscount() { //если эконом вариант скидка 10%, если бизнес скидка 5%
        return getAviaType().equals(AviaType.ECONOMY) ?
                getSum().multiply(BigDecimal.valueOf(0.9)) : getSum().multiply(BigDecimal.valueOf(0.95));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AviaTrip aviaTrip = (AviaTrip) o;
        return Objects.equals(id, aviaTrip.id);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
