package com.viannarp.consultamedica.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long usuarioId;
	
	@NotBlank(message = "O nome não pode ser vazio.")
	@Size(min = 3, max = 255, message = "O nome precisa ser entre 3 e 255 caracteres.")
	private String nome;
	
	@NotBlank(message = "O login não pode ser vazio.")
	@Column(unique = true)
	private String login;
	
	@NotBlank
	@Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
	private String senha;
	
	@NotBlank(message = "O role não pode ser vazio.")
	private String role;

}
