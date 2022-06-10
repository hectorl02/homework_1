package com.training.homework.birds.application.domain.valueObjs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BirdScientificNameTest {
    @Test
    @DisplayName("Null bird scientific name should throw an error")
    void null_bird_scientific_name() {
        // AAA - arrange act assert
        String nullScientificName = null;

        assertThrows(NullPointerException.class, () -> {
            new BirdScientificName(nullScientificName);
        });

    }

    @Test
    @DisplayName("valid product scientific name should not throw an error")
    void valid_bird_scientific_name() {
        // AAA - arrange act assert
        String validScientificName = "la gallina";

        assertDoesNotThrow(() -> {
            new BirdScientificName(validScientificName);
        });
    }
}
