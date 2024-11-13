package com.viannarp.consultamedica.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, 
                                org.springframework.security.core.Authentication authentication) 
                                throws IOException, ServletException {
        // Log para auditoria (opcional)
        if (authentication != null && authentication.getName() != null) {
            System.out.println("Usuário " + authentication.getName() + " desconectado com sucesso.");
        }

        // Redireciona para a página de login com mensagem de logout
        response.sendRedirect("/login?logout");
    }
}
