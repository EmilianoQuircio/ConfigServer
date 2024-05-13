package com.api.configserver.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException exception) throws IOException, ServletException {

        String errMsg = "Priviligi Insufficenti. Impossibile Proseguire.";

        HttpStatus httpStatus = HttpStatus.FORBIDDEN; // 403
        response.setStatus(httpStatus.value());

        //log.warning(errMsg);
        //Map<String, Object> data = new HashMap<>();

        response
                .getOutputStream()
                .println(objectMapper.writeValueAsString(errMsg));
    }


}