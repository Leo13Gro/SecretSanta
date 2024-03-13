package ru.kuimov.secretsanta.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.kuimov.secretsanta.exception.custom.TossException;
import ru.kuimov.secretsanta.exception.response_message.DefaultResponseMessage;

import java.time.ZonedDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class SantaExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<DefaultResponseMessage> noSuchElementExceptionHandler(NoSuchElementException e, HttpServletRequest request){
        return new ResponseEntity<>(new DefaultResponseMessage(ZonedDateTime.now().toString(),
                "No such element", request.getRequestURI()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<DefaultResponseMessage> tossExceptionHandler(TossException e, HttpServletRequest request){
        return new ResponseEntity<>(new DefaultResponseMessage(ZonedDateTime.now().toString(),
                e.getMessage(), request.getRequestURI()),
                HttpStatus.CONFLICT);
    }
}
