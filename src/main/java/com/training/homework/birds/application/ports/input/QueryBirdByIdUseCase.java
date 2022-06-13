package com.training.homework.birds.application.ports.input;

import com.training.homework.commons.UseCase;
import com.training.homework.infrastructure.models.BirdDTO;

import java.util.Optional;

public interface QueryBirdByIdUseCase extends UseCase<Long, Optional<BirdDTO>> {
}
