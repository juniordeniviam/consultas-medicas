package com.viannarp.consultamedica.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.viannarp.consultamedica.service.UsuarioDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	//private final CustomLogoutSuccessHandler logoutSuccessHandler;
	private final UsuarioDetailsService usuarioDetailsService;
	private final CustomLogoutSuccessHandler customLogoutSuccessHandler;
	
    public SecurityConfig(UsuarioDetailsService usuarioDetailsService, CustomLogoutSuccessHandler customLogoutSuccessHandler) {
		this.usuarioDetailsService = usuarioDetailsService;
		this.customLogoutSuccessHandler = customLogoutSuccessHandler;
	}
	
    /*public SecurityConfig(CustomLogoutSuccessHandler logoutSuccessHandler) {
        this.logoutSuccessHandler = logoutSuccessHandler;
    }*/
    
    // Define regras de autorização para as URLs.
    // Configura o formulário de login e logout.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomAccessDeniedHandler accessDeniedHandler) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/api/usuarios").hasRole("ADMIN") // Permite acesso ao endpoint apenas para ADMIN
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").hasRole("ADMIN") // Restringe Swagger para ADMIN
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                //.logoutSuccessHandler(logoutSuccessHandler) // Usa o handler customizado
                .logoutSuccessHandler(customLogoutSuccessHandler)
                .permitAll()
            )
            .csrf(csrf -> csrf
            	.ignoringRequestMatchers("/api/**") // Desativa CSRF para APIs
        	)
            .exceptionHandling(exception -> exception
            	.accessDeniedHandler(accessDeniedHandler) // Usa o handler customizado;
            );

        return http.build();
    }
    
    // Cria usuários em memória com seus respectivos papéis (USER, ADMIN).
    // Utiliza BCryptPasswordEncoder para codificar as senhas.
   /* @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("usuario")
                .password(passwordEncoder().encode("senha"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }*/
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Usando BCrypt para criptografar senhas
    }
    
}
