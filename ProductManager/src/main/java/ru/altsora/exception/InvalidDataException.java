package ru.altsora.exception;

import lombok.Getter;

@Getter
public class InvalidDataException extends RuntimeException {
    private final String message;

    public InvalidDataException(final String message) {
        this.message = message;
    }

    public InvalidDataException(final String format, final Object... params) {
        this.message = String.format(format, params);
    }
}
