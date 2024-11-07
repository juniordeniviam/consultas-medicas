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

import com.viannarp.consultamedica.dto.ConsultaDTO;
import com.viannarp.consultamedica.model.Consulta;
import com.viannarp.consultamedica.repository.ConsultaRepository;
import com.viannarp.consultamedica.repository.PacienteRepository;
import com.viannarp.consultamedica.service.ConsultaService;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {
	
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;
    
    @Autowired
    private ConsultaService consultaService;
    
    
	@GetMapping
	public List<ConsultaDTO> getAllConsultas(){
		return consultaService.listarTodos();
	}
	
    @GetMapping("/{id}")
    public ResponseEntity<Consulta> getConsultaById(@PathVariable Long id) {
        return consultaRepository.findById(id)
                .map(consulta -> ResponseEntity.ok().body(consulta))
                .orElse(ResponseEntity.notFound().build());
    }
	
    @PostMapping
    public ConsultaDTO createConsulta(@RequestBody ConsultaDTO consultaDTO) {
        return consultaService.salvar(consultaDTO);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Consulta> updateConsulta(@PathVariable Long id, @RequestBody Consulta consultaDetails) {
        return consultaRepository.findById(id)
                .map(consulta -> {
                    consulta.setDatahora(consultaDetails.getDatahora());
                    consulta.setPaciente(consultaDetails.getPaciente());
                    Consulta updatedConsulta = consultaRepository.save(consulta);
                    return ResponseEntity.ok().body(updatedConsulta);
                }).orElse(ResponseEntity.notFound().build());
    }  
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteConsulta(@PathVariable Long id) {
        return consultaRepository.findById(id)
                .map(consulta -> {
                    consultaRepository.delete(consulta);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
    

}
