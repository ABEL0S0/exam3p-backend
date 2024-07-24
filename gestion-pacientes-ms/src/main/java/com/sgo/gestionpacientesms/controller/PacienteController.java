package com.sgo.gestionpacientesms.controller;

import com.sgo.gestionpacientesms.entity.PacienteEntity;
import com.sgo.gestionpacientesms.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping("/lista")
    @ResponseStatus(HttpStatus.OK)
    public List<PacienteEntity> getAllPacientes(){
        return pacienteRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<PacienteEntity> getPacienteById(@PathVariable long id){
        return pacienteRepository.findById(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public void createPaciente(@RequestBody PacienteEntity paciente){
        pacienteRepository.save(paciente);
    }

    @DeleteMapping("/del")
    @ResponseStatus(HttpStatus.OK)
    public void deletePaciente(@RequestBody PacienteEntity paciente){
        pacienteRepository.delete(paciente);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> updatePaciente(@RequestBody PacienteEntity paciente){
        if (pacienteRepository.existsById(paciente.getId())) {
            pacienteRepository.save(paciente);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
