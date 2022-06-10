package com.training.homework;

import com.training.homework.birds.application.domain.Bird;
import com.training.homework.birds.application.domain.valueObjs.*;

public class Test {
    public static void main(String[] args) {
        try {
            Bird bird = new Bird(
                    new BirdId(4L),
                    new BirdCommonName("gallina"),
                    new BirdScientificName("Gallus gallus domesticus"),
                    new BirdZoneName("My house"),
                    new BirdConfirmedQuantity(10)
            );
            System.out.println(bird);
        } catch (NullPointerException | IllegalArgumentException e ){
            System.out.println(e.getMessage());
        }
    }
}
