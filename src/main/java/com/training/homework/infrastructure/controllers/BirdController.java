package com.training.homework.infrastructure.controllers;

import com.training.homework.birds.application.ports.input.CreateBirdUseCase;
import com.training.homework.birds.application.ports.input.DeleteBirdUseCase;
import com.training.homework.birds.application.ports.input.QueryBirdByIdUseCase;
import com.training.homework.birds.application.ports.input.UpdateBirdUseCase;
import com.training.homework.infrastructure.models.ApplicationError;
import com.training.homework.infrastructure.models.BirdDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
public class BirdController {
    private final CreateBirdUseCase createBirdUseCase;
    private final UpdateBirdUseCase updateBirdUseCase;
    private final DeleteBirdUseCase deleteBirdUseCase;
    private final QueryBirdByIdUseCase queryBirdByIdUseCase;

    public BirdController(CreateBirdUseCase createBirdUseCase,
                          UpdateBirdUseCase updateBirdUseCase,
                          DeleteBirdUseCase deleteBirdUseCase,
                          QueryBirdByIdUseCase queryBirdByIdUseCase) {
        this.createBirdUseCase = createBirdUseCase;
        this.updateBirdUseCase = updateBirdUseCase;
        this.deleteBirdUseCase = deleteBirdUseCase;
        this.queryBirdByIdUseCase = queryBirdByIdUseCase;
    }

    @RequestMapping(value = "/birds", method = RequestMethod.POST)
    public ResponseEntity<?> createBird(@RequestBody BirdDTO birdDTO) {
        try {
            BirdDTO birdDTOOutput = createBirdUseCase.execute(birdDTO);
            return  ResponseEntity.status(HttpStatus.CREATED).body(birdDTOOutput);
        } catch (NullPointerException | IllegalArgumentException e) {
            ApplicationError applicationError = new ApplicationError( "InputDataValidationError","Bad input data",
                    Map.of("error", e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(applicationError);
        } catch (Exception e){
            if (e.getCause().toString().contains("already exists")) {
                ApplicationError applicationError = new ApplicationError("InputDataValidationError", "Repeat input data", Map.of(
                        "error", "already exists"
                ));
                System.out.println("Error......: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(applicationError);
            } else {
                ApplicationError applicationError = new ApplicationError("SystemError", "Try more later", Map.of());
                System.out.println("Error......: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(applicationError);
            }
        }
    }

    @RequestMapping(value = "/birds", method = RequestMethod.PUT)
    public ResponseEntity<?> updateBird(@RequestBody BirdDTO birdDTO){
        try {
            BirdDTO birdDTOOutput = updateBirdUseCase.execute(birdDTO);
            return ResponseEntity.ok(birdDTOOutput);
        }catch (NullPointerException | IllegalArgumentException exception){
            ApplicationError applicationError = new ApplicationError("SystemError", "Try more later", Map.of());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(applicationError);

        } catch (Exception exception){
            ApplicationError applicationError = new ApplicationError("InputDataValidationError", "Bad input data",
                    Map.of("error", exception.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(applicationError);
        }
    }

    @RequestMapping(value="/birds/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBird(@PathVariable("id") Long id) {
        try {
            Boolean result = deleteBirdUseCase.execute(id);
            if (result) {
                return ResponseEntity.ok("Deleted");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product can not be deleted");
            }
        } catch (NullPointerException | IllegalArgumentException exception) {
            ApplicationError applicationError = new ApplicationError("InpuDataValidationError", "Bad input data",
                    Map.of("error", exception.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(applicationError);
        } catch (Exception exception) {
            ApplicationError applicationError = new ApplicationError("SystemError", "Try more later", Map.of());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(applicationError);
        }
    }

    @RequestMapping(value = "/birds/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getBirdById(@PathVariable("id") Long id) {
        try {
            Optional<BirdDTO> birdDTO = queryBirdByIdUseCase.execute(id);
            if (birdDTO.isPresent()) {
                return ResponseEntity.ok(birdDTO.get());
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No exist bird with this id");
            }
        } catch (NullPointerException | IllegalArgumentException exception) {
            ApplicationError applicationError = new ApplicationError("InputDataValidationError", "Bad input data",
                    Map.of("error", exception.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(applicationError);
        } catch (Exception exception) {
            ApplicationError applicationError = new ApplicationError("SystemError", "Try more later", Map.of());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(applicationError);
        }
    }

}
