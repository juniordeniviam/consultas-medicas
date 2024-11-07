package com.viannarp.consultamedica.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

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
public class PacienteDTO {
	private Long pacienteID;
	
	@CPF
	@NotBlank 
	private String cpf;
	
	@NotBlank(message = "O nome não pode ser vazio.")
	@Size(min = 3, max = 255, message = "O nome precisa ser entre 3 e 255 caracteres.")
	private String nome;
	
	@NotBlank(message = "O sexo não pode ser vazio.")
	private String sexo;
	
	@NotBlank(message = "O telefone não pode ser vazio.")
	private String telefone;

}
