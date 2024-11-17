package com.viannarp.consultamedica.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.viannarp.consultamedica.dto.UsuarioDTO;
import com.viannarp.consultamedica.model.Usuario;
import com.viannarp.consultamedica.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<UsuarioDTO> listarTodos(){
		return usuarioRepository.findAll().stream()
			.map(usuario -> new UsuarioDTO(usuario.getUsuarioId(), usuario.getNome(), usuario.getLogin(), usuario.getSenha(), usuario.getRole()))
			.collect(Collectors.toList());
	}
	
	public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioDTO.getNome());
		usuario.setLogin(usuarioDTO.getLogin());
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuarioDTO.getSenha()));
		usuario.setRole(usuarioDTO.getRole());
		Usuario saved = usuarioRepository.save(usuario);
		usuarioDTO.setUsuarioId(saved.getUsuarioId());
		return usuarioDTO;
	}
	

}
