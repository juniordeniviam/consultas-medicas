package com.viannarp.consultamedica.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
//Gerar Identificador ùnico Universal
public class UuidController {
	
	@GetMapping
	public String GenerateUUID() {
		
		UUID uuid = UUID.randomUUID();
		return "UUID gerado: " + uuid.toString();
		
	}

}
