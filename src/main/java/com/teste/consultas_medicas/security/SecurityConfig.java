package com.teste.consultas_medicas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll() // Permite acesso à página de login e aos arquivos estáticos
                .anyRequest().authenticated() // Requer autenticação para outras páginas
            )
            .formLogin(form -> form
                .loginPage("/login") // Página de login personalizada
                .defaultSuccessUrl("/home", true) // Página redirecionada após login bem-sucedido
                .failureUrl("/login?error=true") // Página redirecionada em caso de falha no login
                .permitAll() // Permite acesso à página de login
            )
            .logout(logout -> logout
                .logoutUrl("/logout") // URL para logout
                .logoutSuccessUrl("/login") // Redireciona após logout
                .invalidateHttpSession(true) // Invalida a sessão
                .clearAuthentication(true) // Limpa a autenticação
                .permitAll() // Permite logout
            );

        configureGlobal(http.getSharedObject(AuthenticationManagerBuilder.class)); // Configuração de autenticação
        return http.build();
    }

    private void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("usuario").password(passwordEncoder().encode("senha")).roles("USER")
            .and()
            .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Usando BCrypt para criptografar senhas
    }
}
