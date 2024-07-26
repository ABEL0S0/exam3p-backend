package com.sgo.fichatecnicams.controller;

import com.sgo.fichatecnicams.entity.FichaTecnicaEntity;
import com.sgo.fichatecnicams.repository.FichaTecnicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/ficha")
public class FichaTecnicaController {

    @Autowired
    private FichaTecnicaRepository repository;

    @GetMapping("/lista")
    @ResponseStatus(HttpStatus.OK)
    public List<FichaTecnicaEntity> getFichaTecnica() {
        return repository.findAll();
    }

    @GetMapping("/paciente/{pacienteId}")
    public List<FichaTecnicaEntity> getFichaTecnicaByPacienteId(@PathVariable Long pacienteId) {
        return repository.findByPacienteId(pacienteId);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public FichaTecnicaEntity crearFichaTecnica(@RequestBody FichaTecnicaEntity ficha) {
        return repository.save(ficha);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateFichaById(@PathVariable Long id, @RequestBody FichaTecnicaEntity ficha) {
        Optional<FichaTecnicaEntity> existingFicha = repository.findById(id);
        if (existingFicha.isPresent()) {
            FichaTecnicaEntity updateFicha = existingFicha.get();
            updateFicha.setDiagnostico(ficha.getDiagnostico());
            updateFicha.setPacienteId(ficha.getPacienteId());
            updateFicha.setPago(ficha.getPago());
            updateFicha.setId(ficha.getId());
            updateFicha.setObservaciones(ficha.getObservaciones());
            updateFicha.setPresupuesto(ficha.getPresupuesto());
            updateFicha.setTratamientos(ficha.getTratamientos());
            updateFicha.setFecha_pago(ficha.getFecha_pago());
            repository.save(updateFicha);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFichaById(@PathVariable long id){
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}