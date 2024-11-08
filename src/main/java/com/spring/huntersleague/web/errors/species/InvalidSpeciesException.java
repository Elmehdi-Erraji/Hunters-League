package com.spring.huntersleague.web.errors.species;

public class InvalidSpeciesException extends RuntimeException {
    public InvalidSpeciesException(String message) {
        super(message);
    }
}