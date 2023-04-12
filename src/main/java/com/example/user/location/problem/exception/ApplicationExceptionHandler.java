package com.example.user.location.problem.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
@RestControllerAdvice
public class ApplicationExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    /**
     * Handles IdNotFoundExceptions and returns an error message with status code NOT_FOUND.
     *
     * @param ex the IdNotFoundException that was thrown
     * @return a Map with an error message
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IdNotFoundException.class)
    public Map<String,String> handleIdNotFoundException(IdNotFoundException ex){
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("error","id not found");
        logger.error(ex.getLocalizedMessage());
        return errorMap;
    }
}

