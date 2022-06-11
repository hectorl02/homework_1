package com.training.homework.birds.application.services;

import com.training.homework.birds.application.ports.input.CreateBirdUseCase;
import com.training.homework.infrastructure.models.BirdDTO;
import org.springframework.stereotype.Service;

@Service
public class CreateBirdService implements CreateBirdUseCase {

    @Override
    public BirdDTO execute(BirdDTO birdDTO) {
        return null;
    }
}
