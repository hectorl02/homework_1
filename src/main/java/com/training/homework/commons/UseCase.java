package com.training.homework.commons;

public interface UseCase <Input, Output>{
    Output execute(Input input);
}
