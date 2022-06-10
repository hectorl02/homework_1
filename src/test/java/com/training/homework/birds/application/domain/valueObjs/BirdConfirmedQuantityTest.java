package com.training.homework.birds.application.domain.valueObjs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BirdConfirmedQuantityTest {
    @Test
    @DisplayName("Null bird confirmed quantity should throw an error")
    void null_bird_confirmed_quantity () {
        // AAA - arrange act assert
        Integer nullConfirmedQuantity = null;

        assertThrows(NullPointerException.class, () -> {
            new BirdConfirmedQuantity(nullConfirmedQuantity);
        });

    }

    @Test
    @DisplayName("valid confirmed quantity name should not throw an error")
    void valid_bird_confirmed_quantity () {
        // AAA - arrange act assert
        Integer validConfirmedQuantity = 125;

        assertDoesNotThrow(() -> {
            new BirdConfirmedQuantity(validConfirmedQuantity);
        });
    }
}
