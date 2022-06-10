package com.training.homework.birds.application.domain.valueObjs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BirdZoneNameTest {
    @Test
    @DisplayName("Null bird zone name should throw an error")
    void null_bird_zone_name() {
        // AAA - arrange act assert
        String nullZoneName = null;

        assertThrows(NullPointerException.class, () -> {
            new BirdZoneName(nullZoneName);
        });

    }

    @Test
    @DisplayName("valid product zone name should not throw an error")
    void valid_bird_zone_name() {
        // AAA - arrange act assert
        String validZoneName = "la gallina";

        assertDoesNotThrow(() -> {
            new BirdZoneName(validZoneName);
        });
    }
}
