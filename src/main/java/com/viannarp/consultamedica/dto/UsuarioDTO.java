package com.viannarp.consultamedica.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioDTO {
	private Long usuarioId;
	
	@NotBlank(message = "O nome não pode ser vazio.")
	@Size(min = 3, max = 255, message = "O nome precisa ser entre 3 e 255 caracteres.")
	private String nome;
	
	@NotBlank(message = "O login não pode ser vazio.")
	@Column(unique = true)
	private String login;
	
	@NotBlank(message = "O role não pode ser vazio.")
	private String role;

}
