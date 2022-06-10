package com.training.homework.birds.application.domain.valueObjs;

import org.apache.commons.lang3.Validate;

public class BirdConfirmedQuantity {
    private final Integer value;

    public BirdConfirmedQuantity(Integer value) {
        Validate.notNull(value, "Bird confirmed quantity can not be null");
        Validate.isTrue(value.toString().length() <= 6, "Bird confirmed quantity can not be longer than 6 characters");
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
