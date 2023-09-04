package ru.aston.davydovskaya_av.task1.exceptions;

import lombok.Getter;
import ru.aston.davydovskaya_av.task1.enums.Code;

@Getter
public class DiscountException extends Exception {

    private final Code code;

    public DiscountException(String message, Code code) {
        super(message);
        this.code = code;
    }
}
