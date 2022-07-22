package com.balance.balancemanager.error;

import com.balance.balancemanager.error.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ResourceErrorResponse> handleUserNotFoundException(ResourceNotFoundException
                                                                            exc) {
        ResourceErrorResponse resourceErrorResponse = new ResourceErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<ResourceErrorResponse>(resourceErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ResourceErrorResponse> handleBadRequestException(Exception exc) {
        ResourceErrorResponse resourceErrorResponse = new ResourceErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<ResourceErrorResponse>(resourceErrorResponse, HttpStatus.BAD_REQUEST);
    }

}
