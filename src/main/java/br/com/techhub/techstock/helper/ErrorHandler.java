package br.com.techhub.techstock.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import br.com.techhub.techstock.config.GlobalDefaultExceptionHandler;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ErrorHandler implements ErrorController {

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @RequestMapping("/error")
    public ResponseEntity<Object> showWhiteLabelErrorPage(@RequestHeader
    Map<String, String> headers,
        HttpServletRequest request,
        HttpServletResponse response
    ) {
        Object status = request.getAttribute(
            RequestDispatcher.ERROR_STATUS_CODE
        );
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            var headersObj = new HttpHeaders();
            MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                ArrayList<String> values = new ArrayList<String>(
                    List.of(entry.getValue())
                );
                List<String> c = multiValueMap.get(entry.getKey());
                if (c != null) {
                    values.addAll(c);
                }

                multiValueMap.put(entry.getKey(), values);
            }


            if (statusCode != HttpStatus.NOT_FOUND.value()) {
                // TODO: mudar?
                return ResponseEntity.status(statusCode).body(status);
            }

            return GlobalDefaultExceptionHandler.staticHandleNotFound(
                new NoHandlerFoundException(
                    request.getMethod(),
                    request.getRequestURL().toString(),
                    headersObj
                ),
                headersObj,
                handlerMapping
            );
        }


        return null;
    }

}
