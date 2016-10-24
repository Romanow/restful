package ru.romanow.restful.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.romanow.restful.model.ErrorResponse;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by romanow on 19.10.16
 */
@ControllerAdvice
public class ExceptionMapper {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionMapper.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody ErrorResponse handleBadRequest(MethodArgumentNotValidException exception) {
        String validationErrors = prepareValidationErrors(exception.getBindingResult().getFieldErrors());
        logger.warn("Bad Request: {}", validationErrors);
        return new ErrorResponse(validationErrors);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public @ResponseBody ErrorResponse handleNotFound(EntityNotFoundException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public @ResponseBody ErrorResponse handleException(Exception exception) {
        logger.error("", exception);
        return new ErrorResponse(exception.getMessage());
    }

    public String prepareValidationErrors(List<FieldError> errors) {
        return errors.stream()
                     .map(err -> "Field " + err.getField() + " has wrong value: [" + err.getDefaultMessage() + "]")
                     .collect(Collectors.joining(";"));
    }
}
