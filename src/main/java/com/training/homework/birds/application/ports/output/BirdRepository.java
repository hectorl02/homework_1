package com.training.homework.birds.application.ports.output;

import com.training.homework.birds.application.domain.Bird;
import com.training.homework.birds.application.domain.valueObjs.BirdId;

import java.util.Optional;

public interface BirdRepository {
    void store(Bird bird);

    Optional<Bird>get(BirdId birdId);

    void update(Bird bird);

    Boolean delete (BirdId birdId);
}
