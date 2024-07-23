package com.sgo.fichatecnicams.data;

import lombok.Data;

import java.sql.Date;

@Data
public class PacienteData {
    private Long id;
    private String nombre;
    private String apellido;
    private Date fecha_nacimiento;
    private String genero;
    private String telefono;
    private String correo;
}