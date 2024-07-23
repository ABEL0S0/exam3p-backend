package com.sgo.fichatecnicams.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "fichas_tecnicas")
@Getter
@Setter
@NoArgsConstructor
public class FichaTecnicaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String diagnostico;
    @Column(name = "paciente_id")
    private Long pacienteId;
    private String observaciones;
    private Long presupuesto;
    private Long pago;
    private Date fecha_pago;
    @OneToMany(mappedBy = "fichaTecnica", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TratamientoEntity> tratamientos;
}