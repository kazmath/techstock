package br.com.techhub.techstock.config;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.techhub.techstock.controller.espelhos.Response;

@ControllerAdvice
public class GlobalDefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
        Exception.class
    })
    protected ResponseEntity<Object> handleConflict(
        RuntimeException exception,
        WebRequest request
    ) {
        if (AnnotationUtils.findAnnotation(
            exception.getClass(),
            ResponseStatus.class
        ) != null) {
            throw exception;
        }

        Response<Object> response = new Response<>();
        response.getErrors()
            .add(
                String.format(
                    "%s: %s",
                    exception.getClass().getSimpleName(),
                    exception.getMessage()
                )
            );
        exception.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
