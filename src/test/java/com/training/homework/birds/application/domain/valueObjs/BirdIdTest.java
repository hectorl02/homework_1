package com.training.homework.birds.application.domain.valueObjs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BirdIdTest {

    @Test
    @DisplayName("Null bird id should throw an error")
    void null_bird_id() {
        // AAA - arrange act assert
        Long invalidId = null;

        assertThrows(NullPointerException.class, () -> {
            new BirdId(invalidId);
        });
    }

    @Test
    @DisplayName("valid bird id should not throw an error")
    void valid_product_id() {
        // AAA - arrange act assert
        Long validId = 2L;

        assertDoesNotThrow(() -> {
            new BirdId(validId);
        });
    }

}
