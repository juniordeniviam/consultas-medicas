package com.viannarp.consultamedica.dto;

import java.time.LocalDateTime;

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
public class ConsultaDTO {

	private Long consultaID;
	private LocalDateTime datahora;
	private Long pacienteID;
	
}
