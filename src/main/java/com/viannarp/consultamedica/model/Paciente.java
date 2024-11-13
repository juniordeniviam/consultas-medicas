package com.viannarp.consultamedica.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	private String cpf;
	private String nome;
	private String sexo;
	private String telefone;
	
	//@OneToMany(mappedBy = "paciente")
	//private List<Consulta> consultas;
	

}
