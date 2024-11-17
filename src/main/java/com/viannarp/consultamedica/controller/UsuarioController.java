package com.viannarp.consultamedica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viannarp.consultamedica.dto.UsuarioDTO;
import com.viannarp.consultamedica.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public List<UsuarioDTO> getAllUsers(){
		return usuarioService.listarTodos();
	}

	@PostMapping
	public UsuarioDTO createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
		return usuarioService.salvar(usuarioDTO);
	}

}
