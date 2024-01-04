package com.example.cyrilus.recetus.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        // Récupérez les erreurs de validation du BindingResult
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

        // Retournez une réponse avec le statut HTTP 400 Bad Request et le message d'erreur
        return ResponseEntity.badRequest().body(errorMessage);
    }
}
