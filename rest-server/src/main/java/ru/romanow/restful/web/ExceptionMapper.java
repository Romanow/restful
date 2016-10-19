package ru.romanow.restful.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by romanow on 19.10.16
 */
@ControllerAdvice(annotations = RestController.class)
public class ExceptionMapper {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionMapper.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void handleBadRequest(MethodArgumentNotValidException exception) {
        logger.warn("Bad Request: {}", prepareValidationErrors(exception.getBindingResult().getFieldErrors()));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public void handleException(Exception exception) {
        logger.error("", exception);
    }

    public String prepareValidationErrors(List<FieldError> errors) {
        return errors.stream()
                     .map(err -> "Field " + err.getField() + " has wrong value: [" + err.getDefaultMessage() + "]")
                     .collect(Collectors.joining(";"));
    }
}
