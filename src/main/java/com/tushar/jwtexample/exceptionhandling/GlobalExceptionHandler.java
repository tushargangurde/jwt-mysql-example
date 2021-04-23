package com.tushar.jwtexample.exceptionhandling;

import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InputFieldEmptyException.class)
    public ResponseEntity<ApiError> handleInputFieldEmptyException(InputFieldEmptyException exception) {
        ApiError apiError = new ApiError();
        apiError.setErrorCode(400);
        apiError.setErrorMessage("Input fields are empty, please look into it");
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidUsernamePasswordException.class)
    public ResponseEntity<ApiError> handleInvalidUsernamePasswordException(InvalidUsernamePasswordException exception) {
        ApiError apiError = new ApiError();
        apiError.setErrorCode(401);
        apiError.setErrorMessage("Username or Password is invalid");
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ApiError> handleSignatureException(SignatureException exception) {
        ApiError apiError = new ApiError();
        apiError.setErrorCode(406);
        apiError.setErrorMessage(exception.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_ACCEPTABLE);
    }
}
