package ru.altsora.handler;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.altsora.exception.DomainNotFoundException;
import ru.altsora.exception.InvalidDataException;

@ControllerAdvice
public class AwesomeExceptionHandler extends ResponseEntityExceptionHandler {
    public static final int NOT_FOUND_CODE = 1;
    public static final int INVALID_DATA_CODE = 2;

    @ExceptionHandler(DomainNotFoundException.class)
    private ResponseEntity<ResponseException> handleDomainNotFoundException(DomainNotFoundException ex) {
        final ResponseException response = new ResponseException(NOT_FOUND_CODE, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidDataException.class)
    private ResponseEntity<ResponseException> handleInvalidDataException(InvalidDataException ex) {
        final ResponseException response = new ResponseException(INVALID_DATA_CODE, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Getter
    @AllArgsConstructor
    private static class ResponseException {
        @JsonProperty("retCode")
        private final int retCode;
        @JsonProperty("retMessage")
        private final String retMessage;
    }
}
