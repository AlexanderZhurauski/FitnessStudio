package org.mycompany.fitness.core.exceptions.handlers;

import org.mycompany.fitness.core.exceptions.custom.EntityNotFoundException;
import org.mycompany.fitness.core.exceptions.errors.ErrorField;
import org.mycompany.fitness.core.exceptions.errors.MultipleErrorResponse;
import org.mycompany.fitness.core.exceptions.errors.SingleErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<List<SingleErrorResponse>> handleEntityNotFound(EntityNotFoundException ex) {
        SingleErrorResponse errorResponse = new SingleErrorResponse();
        errorResponse.setLogref(ex.getClass().getSimpleName());
        errorResponse.setMessage(ex.getMessage());

        return ResponseEntity.internalServerError().body(List.of(errorResponse));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        MultipleErrorResponse errorResponse = new MultipleErrorResponse();
        List<ErrorField> errorFields = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(objectError -> new ErrorField(objectError.getDefaultMessage(),
                        objectError.getObjectName()))
                .collect(Collectors.toList());

        errorResponse.setLogref(ex.getMessage());
        errorResponse.setErrors(errorFields);

        return ResponseEntity.badRequest().body(errorResponse);
    }
}
