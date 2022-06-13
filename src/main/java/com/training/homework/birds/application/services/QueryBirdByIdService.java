package com.training.homework.birds.application.services;

import com.training.homework.birds.application.domain.Bird;
import com.training.homework.birds.application.domain.valueObjs.BirdId;
import com.training.homework.birds.application.ports.input.QueryBirdByIdUseCase;
import com.training.homework.birds.application.ports.output.BirdRepository;
import com.training.homework.infrastructure.models.BirdDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QueryBirdByIdService implements QueryBirdByIdUseCase {
    private final BirdRepository birdRepository;

    public QueryBirdByIdService(BirdRepository birdRepository) {
        this.birdRepository = birdRepository;
    }

    @Override
    public Optional<BirdDTO> execute(Long id) {
        BirdId birdId = new BirdId(id);

        Optional<Bird> birdOptional = birdRepository.get(birdId);

        return birdOptional.map(product -> {
            BirdDTO birtDTO = BirdDTO.fromDomain(product);
            return birtDTO;
        });
    }
}
