package com.sgo.odontoms.client;

import com.sgo.odontoms.data.PacienteData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "gestion-pacientes-ms")
public interface PacienteClient {
    @GetMapping("/pacientes/{id}")
    PacienteData getPacienteById(@PathVariable("id") Long id);
}