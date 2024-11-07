package com.viannarp.consultamedica.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viannarp.consultamedica.dto.ConsultaDTO;
import com.viannarp.consultamedica.model.Consulta;
import com.viannarp.consultamedica.model.Paciente;
import com.viannarp.consultamedica.repository.ConsultaRepository;
import com.viannarp.consultamedica.repository.PacienteRepository;

@Service
public class ConsultaService {
	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	public List<ConsultaDTO> listarTodos(){
		return consultaRepository.findAll().stream()
			.map(consulta -> new ConsultaDTO(consulta.getConsultaID(), consulta.getDatahora(), consulta.getPaciente().getPacienteID()))
			.collect(Collectors.toList());
	}
	
	public ConsultaDTO salvar(ConsultaDTO consultaDTO) {
		Paciente paciente = pacienteRepository.findById(consultaDTO.getPacienteID())
			.orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
		
		Consulta consulta = new Consulta();
		
		consulta.setDatahora(consultaDTO.getDatahora());
		consulta.setPaciente(paciente);
		
		Consulta saved = consultaRepository.save(consulta);
		consultaDTO.setConsultaID(saved.getConsultaID());
		
		return consultaDTO;
	}
	

}
