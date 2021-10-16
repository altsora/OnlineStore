package ru.altsora.exception;

import lombok.Getter;

@Getter
public class DomainNotFoundException extends RuntimeException {
    private final String message;

    public DomainNotFoundException(final String messageFormat, final Object... params) {
        this.message = String.format(messageFormat, params);
    }
}
