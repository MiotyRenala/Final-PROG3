package com.Federation.Final.datasource;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class ApiKeyInterceptor implements HandlerInterceptor {

    @Value("${api.key}")
    private String validApiKey;

    private  final String API_KEY_HEADER = "x-api-key";

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {

        String apiKey = request.getHeader(API_KEY_HEADER);

        if (apiKey == null || apiKey.isBlank()) {
            sendUnauthorizedResponse(response);
            return false;
        }

        if (!validApiKey.equals(apiKey)) {
            sendUnauthorizedResponse(response);
            return false;
        }

        return true;
    }

    private void sendUnauthorizedResponse(HttpServletResponse response)
            throws Exception {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");

        response.getWriter().write("""
            {
              "error": "Bad credentials"
            }
        """);
    }
}
