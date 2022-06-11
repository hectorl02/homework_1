package com.training.homework.infrastructure.adapter.output;

import com.training.homework.birds.application.domain.Bird;
import com.training.homework.birds.application.domain.valueObjs.BirdId;
import com.training.homework.birds.application.ports.output.BirdRepository;

import java.util.Optional;

public class PostgresSqlBirdRepository implements BirdRepository {
    @Override
    public void store(Bird bird) {

    }

    @Override
    public Optional<Bird> get(BirdId birdId) {
        return Optional.empty();
    }

    @Override
    public void update(Bird bird) {

    }

    @Override
    public Boolean delete(BirdId birdId) {
        return null;
    }
}
