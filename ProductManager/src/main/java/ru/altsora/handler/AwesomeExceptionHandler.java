package ru.altsora.handler;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.altsora.exception.DomainNotFoundException;
import ru.altsora.exception.InvalidDataException;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static ru.altsora.handler.AwesomeExceptionHandler.ErrorType.*;

@ControllerAdvice
public class AwesomeExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DomainNotFoundException.class)
    private ResponseEntity<ErrorWrapper> handleDomainNotFoundException(DomainNotFoundException ex) {
        final String msg = ex.getMessage();
        final ErrorWrapper error = new ErrorWrapper(DOMAIN_NOT_FOUND, msg);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidDataException.class)
    private ResponseEntity<ErrorWrapper> handleInvalidDataException(InvalidDataException ex) {
        final String msg = ex.getMessage();
        final ErrorWrapper error = new ErrorWrapper(INVALID_DATA, msg);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLException.class)
    private ResponseEntity<ErrorWrapper> handleSQLException(SQLException ex) {
        final String msg = ex.getMessage();
        final ErrorWrapper error = new ErrorWrapper(SQL, msg);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final ErrorWrapper error = new ErrorWrapper(INVALID_DATA);
        final String format = "Атрибут: '%s', проблема: '%s'";
        ex.getBindingResult().getFieldErrors().forEach(err -> {
            final String field = err.getField();
            final String msg = err.getDefaultMessage();
            error.addError(format, field, msg);
        });
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    private ResponseEntity<Object> handleMy(ConstraintViolationException ex) {
        final ErrorWrapper error = new ErrorWrapper(INVALID_DATA);
        final String format = "Параметр: '%s', проблема: '%s'";

        ex.getConstraintViolations().forEach(err -> {
            final String field = err.getPropertyPath().toString().split("\\.")[1]; // method.variable
            final String msg = err.getMessage();
            error.addError(format, field, msg);
        });

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @Getter
    private static class ErrorWrapper {
        @JsonProperty("retCode")
        private final int retCode;
        @JsonProperty("errorType")
        private final ErrorType errorType;
        @JsonProperty("errorMessages")
        private final Set<String> errors = new HashSet<>();

        public ErrorWrapper(ErrorType errorType) {
            this.errorType = errorType;
            this.retCode = errorType.getCode();
        }

        public ErrorWrapper(ErrorType errorType, String msg) {
            this(errorType);
            addError(msg);
        }

        public void addError(String format, Object... args) {
            errors.add(String.format(format, args));
        }
    }

    @Getter
    @AllArgsConstructor
    enum ErrorType {
        DOMAIN_NOT_FOUND(1),
        INVALID_DATA(2),
        SQL(3);
        private final int code;
    }
}
