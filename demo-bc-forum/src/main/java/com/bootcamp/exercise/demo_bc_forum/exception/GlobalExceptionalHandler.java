package com.bootcamp.exercise.demo_bc_forum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import com.bootcamp.exercise.demo_bc_forum.model.dto.ErrorResponseDTO;

@RestControllerAdvice 
public class GlobalExceptionalHandler {
  
    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<ErrorResponseDTO> restClientExceptionHandler(RestClientException ex) {
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .code(3)
                .message("RestTemplate Error - JsonPlaceHolder")
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(JPHRestClientException.class)
    public ResponseEntity<ErrorResponseDTO> jphRestClientExceptionHandler(JPHRestClientException ex) {
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .code(4)
                .message("Json Placeholder Service Unavailable. Please try again later.")
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> userNotFoundExceptionHandler(UserNotFoundException ex) {
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .code(1)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidUserInputException.class)
    public ResponseEntity<ErrorResponseDTO> invalidUserInputExceptionHandler(InvalidUserInputException ex) {
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .code(2)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .code(2)
                .message("Invalid input." )
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
