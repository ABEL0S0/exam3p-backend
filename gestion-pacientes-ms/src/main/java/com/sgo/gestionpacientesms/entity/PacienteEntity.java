package com.sgo.gestionpacientesms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;


@Entity
@Table(name = "pacientes")
@Getter
@Setter
@NoArgsConstructor
public class PacienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String apellido;

    private Date fecha_nacimiento;

    private String genero;

    private String telefono;

    private String correo;

}
