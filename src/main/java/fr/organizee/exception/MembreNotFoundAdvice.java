package fr.organizee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class MembreNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(MembreNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String membreNotFoundHandler(MembreNotFoundException ex){return ex.getMessage();}
}
