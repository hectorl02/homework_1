package com.training.homework.birds.application.services;

import com.training.homework.birds.application.domain.Bird;
import com.training.homework.birds.application.ports.input.UpdateBirdUseCase;
import com.training.homework.birds.application.ports.output.BirdRepository;
import com.training.homework.infrastructure.models.BirdDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateBirdService implements UpdateBirdUseCase {
    private final BirdRepository birdRepository;

    public UpdateBirdService(BirdRepository birdRepository) {
        this.birdRepository = birdRepository;
    }

    @Override
    public BirdDTO execute(BirdDTO birdDTO) {

        Bird bird = birdDTO.toDomain();

        Optional<Bird> birdBD =birdRepository.get(bird.getId());

        if (birdBD.isPresent()){
            birdRepository.update(bird);
            birdDTO.setStatus("update");
        } else{
            birdDTO.setStatus("No updated");
        }
        return birdDTO;
    }

}
