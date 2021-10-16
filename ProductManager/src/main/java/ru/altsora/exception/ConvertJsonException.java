package ru.altsora.exception;

/**
 * Исключение бросается в случае ошибки конвертации из/в JSON.
 */
public class ConvertJsonException extends RuntimeException {
    public ConvertJsonException(Throwable cause) {
        super(cause);
    }
}
