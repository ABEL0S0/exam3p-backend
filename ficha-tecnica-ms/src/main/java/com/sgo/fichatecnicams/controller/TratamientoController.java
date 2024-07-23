package com.sgo.fichatecnicams.controller;

import com.sgo.fichatecnicams.entity.TratamientoEntity;
import com.sgo.fichatecnicams.repository.TratamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tratamiento")
public class TratamientoController {

    @Autowired
    private TratamientoRepository repository;

    @GetMapping("/lista")
    @ResponseStatus(HttpStatus.OK)
    public List<TratamientoEntity> getTratamientos() {
        return repository.findAll();
    }

    @GetMapping("/lista/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TratamientoEntity getTratamientoById(@PathVariable Long id) {
        return repository.getById(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public void crearTratamiento(@RequestBody TratamientoEntity tratamiento) {
        repository.save(tratamiento);
    }

    @DeleteMapping("/del")
    @ResponseStatus(HttpStatus.OK)
    public void eliminarTratamiento(@RequestBody TratamientoEntity tratamiento) {
        repository.delete(tratamiento);
    }

}
