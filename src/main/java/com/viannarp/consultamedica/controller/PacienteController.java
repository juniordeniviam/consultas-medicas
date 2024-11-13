package com.viannarp.consultamedica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viannarp.consultamedica.dto.PacienteDTO;
import com.viannarp.consultamedica.model.Paciente;
import com.viannarp.consultamedica.repository.PacienteRepository;
import com.viannarp.consultamedica.service.PacienteService;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private PacienteService pacienteService;
	
	@GetMapping
	public List<PacienteDTO> getAllPacientes(){
		return pacienteService.listarTodos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Paciente> getPacienteById(@PathVariable Long id){
		return pacienteRepository.findById(id)
				.map(paciente -> ResponseEntity.ok().body(paciente))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public PacienteDTO createPaciente(@RequestBody PacienteDTO pacienteDTO) {
		return pacienteService.salvar(pacienteDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Paciente> updatePaciente(@PathVariable Long id, @RequestBody Paciente pacienteDetails){
		return pacienteRepository.findById(id)
				.map(paciente ->{
					paciente.setCpf(pacienteDetails.getCpf());
					paciente.setNome(pacienteDetails.getNome());
					paciente.setSexo(pacienteDetails.getSexo());
					paciente.setTelefone(pacienteDetails.getTelefone());
					Paciente updatePaciente = pacienteRepository.save(paciente);
					return ResponseEntity.ok().body(updatePaciente);					
				}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePaciente(@PathVariable Long id){
		return pacienteRepository.findById(id)
			.map(paciente -> {
				pacienteRepository.delete(paciente);
				return ResponseEntity.ok().build();
			}).orElse(ResponseEntity.notFound().build());
	}

}
