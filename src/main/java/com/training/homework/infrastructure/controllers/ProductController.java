package com.training.homework.infrastructure.controllers;

import com.training.homework.birds.application.ports.input.CreateBirdUseCase;
import com.training.homework.infrastructure.models.ApplicationError;
import com.training.homework.infrastructure.models.BirdDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ProductController {
    private final CreateBirdUseCase createBirdUseCase;

    public ProductController(CreateBirdUseCase createBirdUseCase) {
        this.createBirdUseCase = createBirdUseCase;
    }

    public ResponseEntity<?> createBird(@RequestBody BirdDTO birdDTO) {
        try {
            BirdDTO birdDTOOutput = createBirdUseCase.execute(birdDTO);
            return  ResponseEntity.ok(birdDTOOutput);
        } catch (NullPointerException | IllegalArgumentException e) {
            ApplicationError applicationError = new ApplicationError( "InputDataValidationError","Bad input data", Map.of("error", e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(applicationError);
        } catch (Exception e){
            ApplicationError applicationError = new ApplicationError("SystemError", "Try more later", Map.of());
            System.out.println("Error......: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(applicationError);
        }

    }
}
