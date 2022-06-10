package com.training.homework.birds.application.domain.valueObjs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BirdCommonNameTest {
    @Test
    @DisplayName("Null bird common name should throw an error")
    void null_bird_name() {
        // AAA - arrange act assert
        String nullName = null;

        assertThrows(NullPointerException.class, () -> {
            new BirdCommonName(nullName);
        });

    }

    @Test
    @DisplayName("valid product common name should not throw an error")
    void valid_bird_name() {
        // AAA - arrange act assert
        String validName = "la gallina";

        assertDoesNotThrow(() -> {
            new BirdCommonName(validName);
        });
    }
}
