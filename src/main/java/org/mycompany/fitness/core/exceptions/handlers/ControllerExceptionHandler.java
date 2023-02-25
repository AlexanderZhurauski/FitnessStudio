package org.mycompany.fitness.core.exceptions.handlers;

import org.mycompany.fitness.core.exceptions.errors.ErrorField;
import org.mycompany.fitness.core.exceptions.errors.MultipleErrorResponse;
import org.mycompany.fitness.core.exceptions.errors.SingleErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<List<SingleErrorResponse>> handleMissingPathVariableException(MissingPathVariableException ex) {
        String message = "The required path variable '" + ex.getVariableName() + "' is missing from the request URL.";
        SingleErrorResponse errorResponse = new SingleErrorResponse("Missing path variable", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of(errorResponse));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<List<SingleErrorResponse>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String message = "The request body is not readable or is missing required fields.";
        SingleErrorResponse errorResponse = new SingleErrorResponse("Invalid request body", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of(errorResponse));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MultipleErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

        MultipleErrorResponse errorResponse = new MultipleErrorResponse();
        List<ErrorField> errorFields = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new ErrorField(fieldError.getDefaultMessage(),
                        fieldError.getField()))
                .collect(Collectors.toList());

        errorResponse.setLogref("Client-side error: Invalid field data!");
        errorResponse.setErrors(errorFields);

        return ResponseEntity.badRequest().body(errorResponse);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<List<SingleErrorResponse>> handleEntityNotFound(RuntimeException ex) {
        SingleErrorResponse errorResponse = new SingleErrorResponse();
        errorResponse.setLogref(ex.getClass().getSimpleName());
        errorResponse.setMessage(ex.getMessage());

        return ResponseEntity.internalServerError().body(List.of(errorResponse));
    }
}
