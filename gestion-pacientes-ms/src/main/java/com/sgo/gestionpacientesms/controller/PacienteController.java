package com.sgo.gestionpacientesms.controller;

import com.sgo.gestionpacientesms.entity.PacienteEntity;
import com.sgo.gestionpacientesms.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping("/list")
    public List<PacienteEntity> getAllPacientes(){
        return pacienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<PacienteEntity> getPacienteById(@PathVariable long id){
        return pacienteRepository.findById(id);
    }

    @PostMapping("/save")
    public void createPaciente(@RequestBody PacienteEntity paciente){
        pacienteRepository.save(paciente);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePacienteById(@PathVariable long id){
        if (pacienteRepository.existsById(id)) {
            pacienteRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updatePacienteById(@PathVariable long id, @RequestBody PacienteEntity paciente){
        Optional<PacienteEntity> existingPaciente = pacienteRepository.findById(id);
        if (existingPaciente.isPresent()) {
            PacienteEntity updatedPaciente = existingPaciente.get();
            updatedPaciente.setNombre(paciente.getNombre());
            updatedPaciente.setApellido(paciente.getApellido());
            updatedPaciente.setId(paciente.getId());
            updatedPaciente.setTelefono(paciente.getTelefono());
            updatedPaciente.setCorreo(paciente.getCorreo());
            updatedPaciente.setFecha_nacimiento(paciente.getFecha_nacimiento());
            updatedPaciente.setGenero(paciente.getGenero());
            pacienteRepository.save(updatedPaciente);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
