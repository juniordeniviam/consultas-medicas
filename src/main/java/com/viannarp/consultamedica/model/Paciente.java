package com.viannarp.consultamedica.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pacienteID;
	
	// Garantia da integridade dos dados no nível da aplicação, considerarndo o uso das anotações de validação do Jakarta Validation
	@NotNull
	@Size(min = 11, max = 11)
	@Column(unique = true, nullable = false)
	private String cpf;
	

	@NotBlank
	private String nome;
	
	@NotBlank
	@Pattern(regexp = "M|F|O") // Masculino|Feminino|Outro
	private String sexo;
	
	@NotBlank
	@Pattern(regexp = "\\d{10,11}") // Para telefones com 10 ou 11 dígitos
	private String telefone;
	
	//@OneToMany(mappedBy = "paciente")
	//private List<Consulta> consultas;
	

}
