package com.sgo.fichatecnicams.repository;

import com.sgo.fichatecnicams.entity.FichaTecnicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FichaTecnicaRepository extends JpaRepository<FichaTecnicaEntity,Long> {
    List<FichaTecnicaEntity> findByPacienteId(Long pacienteId);
}