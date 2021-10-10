package me.plich.empik.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Error> userNotFound() {
        Error error = new Error(
                LocalDateTime.now(),
                "User not found",
                HttpStatus.NOT_FOUND.value()
        );
        return new ResponseEntity<> (error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SomeServerException.class)
    public ResponseEntity<Error> serverError() {
        Error error = new Error(
                LocalDateTime.now(),
                "Server error, please try again later",
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return new ResponseEntity<> (error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
