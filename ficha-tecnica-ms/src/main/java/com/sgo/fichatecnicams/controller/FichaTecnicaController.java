package com.sgo.fichatecnicams.controller;

import com.sgo.fichatecnicams.entity.FichaTecnicaEntity;
import com.sgo.fichatecnicams.repository.FichaTecnicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}