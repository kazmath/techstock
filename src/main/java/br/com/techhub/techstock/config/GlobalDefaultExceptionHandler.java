package br.com.techhub.techstock.config;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.PathContainer;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.techhub.techstock.controller.espelhos.Response;


@ControllerAdvice
public class GlobalDefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    // TODO: Tratar erros de autenticação
    // @ExceptionHandler(value = {
    //     AuthorizationDeniedException.class
    // })
    // protected ResponseEntity<Object> handleAccessDeniedConflict(
    //     RuntimeException exception,
    //     WebRequest request
    // ) {
    //     return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");
    // }


    @Override
    @Nullable
    @SuppressWarnings("null")
    protected ResponseEntity<Object> handleNoHandlerFoundException(
        NoHandlerFoundException ex,
        HttpHeaders headers,
        HttpStatusCode status,
        WebRequest request
    ) {

        return staticHandleNotFound(ex, headers, handlerMapping);

    }

    @SuppressWarnings("null")
    public static ResponseEntity<Object> staticHandleNotFound(
        NoHandlerFoundException ex,
        HttpHeaders headers,
        RequestMappingHandlerMapping handlerMapping
    ) {
        if (ex.getRequestURL().startsWith("/techstock")) {
            return ResponseEntity.status(404).build();
        }
        Set<RequestMappingInfo> rmSet = handlerMapping.getHandlerMethods()
            .keySet();
        for (RequestMappingInfo rm : rmSet) {
            String newURL = "/techstock" + ex.getRequestURL();

            try {
                if (rm.getPathPatternsCondition()
                    .getPatterns()
                    .removeIf(
                        t -> t.matches(PathContainer.parsePath(newURL))
                    )) {
                    HttpHeaders newHeaders = new HttpHeaders();
                    newHeaders.addAll(headers);
                    newHeaders.set("Location", newURL);

                    return new ResponseEntity<>(
                        null,
                        newHeaders,
                        HttpStatus.FOUND
                    );
                }
            } catch (Exception e) {
            }
        }

        return ResponseEntity.status(404).build();
    }

    @ExceptionHandler
    protected ResponseEntity<Object> handleGenericConflict(
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
