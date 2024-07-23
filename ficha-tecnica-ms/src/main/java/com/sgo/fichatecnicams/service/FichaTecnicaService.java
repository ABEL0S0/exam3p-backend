package com.sgo.fichatecnicams.service;


import com.sgo.fichatecnicams.client.PacienteClient;
import com.sgo.fichatecnicams.data.PacienteData;
import com.sgo.fichatecnicams.entity.FichaTecnicaEntity;
import com.sgo.fichatecnicams.repository.FichaTecnicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FichaTecnicaService {

    @Autowired
    private FichaTecnicaRepository fichaTecnicaRepository;

    @Autowired
    private PacienteClient pacienteClient;

    public FichaTecnicaEntity createFichaTecnica(FichaTecnicaEntity fichaTecnica) {
        // Llamada al servicio de pacientes para obtener información adicional
        PacienteData paciente = pacienteClient.getPacienteById(fichaTecnica.getPacienteId());
        if (paciente == null) {
            throw new RuntimeException("Paciente no encontrado");
        }

        // Lógica de creación de la ficha técnica
        return fichaTecnicaRepository.save(fichaTecnica);
    }
}
