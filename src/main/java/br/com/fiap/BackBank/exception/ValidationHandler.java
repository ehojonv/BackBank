package br.com.fiap.BackBank.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationHandler {

    private record ValidationError(String field, String message) {
        public ValidationError(FieldError fe) {
            this(fe.getField(), fe.getDefaultMessage());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public List<ValidationError> handler(MethodArgumentNotValidException ex) {
        return ex.getFieldErrors()
                .stream()
                .map(ValidationError::new)
                .toList();
    }

}
