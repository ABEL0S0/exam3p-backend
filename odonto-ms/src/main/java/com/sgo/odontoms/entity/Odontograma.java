package com.sgo.odontoms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "odontogramas")
@Getter
@Setter
@NoArgsConstructor
public class Odontograma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String img_url;
    @Column(name = "paciente_id")
    private Long pacienteId;
}
