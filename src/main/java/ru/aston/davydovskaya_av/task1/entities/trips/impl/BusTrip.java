package ru.aston.davydovskaya_av.task1.entities.trips.impl;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import ru.aston.davydovskaya_av.task1.entities.trips.Trip;
import ru.aston.davydovskaya_av.task1.enums.BusType;
import ru.aston.davydovskaya_av.task1.enums.Code;
import ru.aston.davydovskaya_av.task1.exceptions.DiscountException;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
public class BusTrip extends Trip {

    private BusType busType;

    @SneakyThrows
    @Override
    public BigDecimal getDiscount() { //если двухэтажный автобус скидка 25%, если с одним этажом 15%
        if (getSum().compareTo(BigDecimal.valueOf(800)) <= 0) {
            throw new DiscountException("To receive a discount, the sum must be more than 800", Code.DISCOUNT_BUS_EXCEPTION);
        }

        return getBusType().equals(BusType.TWO_STORY) ?
                getSum().multiply(BigDecimal.valueOf(0.75)) : getSum().multiply(BigDecimal.valueOf(0.85));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusTrip busTrip = (BusTrip) o;
        return Objects.equals(id, busTrip.id);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
