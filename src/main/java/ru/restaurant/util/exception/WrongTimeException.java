package ru.restaurant.util.exception;

public class WrongTimeException extends RuntimeException{
    public WrongTimeException(String message) {
        super(message);
    }
}
