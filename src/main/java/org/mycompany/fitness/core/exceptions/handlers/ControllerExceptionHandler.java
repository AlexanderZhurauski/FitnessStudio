package org.mycompany.fitness.core.exceptions.handlers;

import org.mycompany.fitness.core.exceptions.custom.EntityNotFoundException;
import org.mycompany.fitness.core.exceptions.errors.SingleErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<List<SingleErrorResponse>> handleEntityNotFound(EntityNotFoundException ex) {
        SingleErrorResponse errorResponse = new SingleErrorResponse();
        errorResponse.setLogref(ex.getClass().getSimpleName());
        errorResponse.setMessage(ex.getMessage());

        return ResponseEntity.internalServerError().body(List.of(errorResponse));
    }


}
