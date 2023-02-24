package com.ista.school.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "profesor_curso_materia",uniqueConstraints=@UniqueConstraint(columnNames = {"id_profesor","id_curso","id_materia"}))
public class ProfesorCursoMateria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_pro_mate_curso;

    @ManyToOne
    @JoinColumn(name = "id_profesor",referencedColumnName = "id_profesor")
    private Profesor profesor;

    @ManyToOne
    @JoinColumn(name = "id_curso",referencedColumnName = "id_curso")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "id_materia",referencedColumnName = "id_materia")
    private Materia materia;

}
