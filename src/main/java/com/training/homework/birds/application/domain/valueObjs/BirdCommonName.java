package com.training.homework.birds.application.domain.valueObjs;

import org.apache.commons.lang3.Validate;

public class BirdCommonName {
    private final String value;

    public BirdCommonName(String value) {
        Validate.notNull(value,"Bird name can not be null");
        Validate.isTrue(!value.isEmpty(), "Bird name can not be empty");
        Validate.isTrue(value.length() <= 30, "Bird name can not be longer than 30 characters");
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
