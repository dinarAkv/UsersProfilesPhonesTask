package ru.testtask.taskuser.excpetions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Long entityId, Class entityClass) {
        super(String.format("Entity $s with id %d not found", entityClass.getSimpleName(), entityId));
    }
}
