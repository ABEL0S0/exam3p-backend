package com.sgo.odontoms.repository;

import com.sgo.odontoms.entity.Odontograma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OdontogramaRepository extends JpaRepository<Odontograma, Long> {
    List<Odontograma> findByPacienteId(Long pacienteId);
}
