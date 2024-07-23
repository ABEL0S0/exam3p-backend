package com.sgo.fichatecnicams.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "tratamientos")
@Getter
@Setter
@NoArgsConstructor
public class TratamientoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tratamiento;
    private Date fecha_inicio;
    private Date fecha_fin;
    private Long costo;
    private Long ficha_id;
}
