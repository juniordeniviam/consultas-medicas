package com.viannarp.consultamedica.service;

import com.viannarp.consultamedica.model.Usuario;
import com.viannarp.consultamedica.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
        
        System.out.println("Usuário autenticado: " + usuario.getLogin());
        System.out.println("Role do usuário: " + usuario.getRole());

        return User.builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha()) // Deve estar criptografada
                .roles(usuario.getRole()) // Papel deve ser passado como ROLE_USER ou ROLE_ADMIN
                .build();
    }
}
