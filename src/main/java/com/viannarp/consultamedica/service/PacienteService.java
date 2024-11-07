package com.viannarp.consultamedica.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viannarp.consultamedica.dto.PacienteDTO;
import com.viannarp.consultamedica.model.Paciente;
import com.viannarp.consultamedica.repository.PacienteRepository;

@Service
public class PacienteService {
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	public List<PacienteDTO> listarTodos(){
		return pacienteRepository.findAll().stream()
			.map(paciente -> new PacienteDTO(paciente.getPacienteID(), paciente.getCpf(), paciente.getNome(), paciente.getSexo(), paciente.getTelefone()))
			.collect(Collectors.toList());
	}
	
	public PacienteDTO salvar(PacienteDTO pacienteDTO) {
		Paciente paciente = new Paciente();
		paciente.setCpf(pacienteDTO.getCpf());
		paciente.setNome(pacienteDTO.getNome());
		paciente.setSexo(pacienteDTO.getSexo());
		paciente.setTelefone(pacienteDTO.getTelefone());
		Paciente saved = pacienteRepository.save(paciente);
		pacienteDTO.setPacienteID(saved.getPacienteID());
		return pacienteDTO;
	}
	

}
