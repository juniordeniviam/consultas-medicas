package com.viannarp.consultamedica.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) 
            throws IOException, ServletException {
        // Log para auditoria (opcional)
        System.out.println("Acesso negado para: " + request.getRequestURI());

        // Redireciona ou responde com mensagem personalizada
        response.sendRedirect("/access-denied"); // Página de acesso negado
    }
}
