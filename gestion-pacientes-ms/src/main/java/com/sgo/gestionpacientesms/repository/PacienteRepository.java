package com.sgo.gestionpacientesms.repository;

import com.sgo.gestionpacientesms.entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<PacienteEntity,Long> {
}
