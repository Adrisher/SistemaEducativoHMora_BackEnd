package com.ista.school.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "detalle", uniqueConstraints=@UniqueConstraint(columnNames = {"id_matricula","id_materia"}))
public class Detalle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_detalle;

    @ManyToOne
    @JoinColumn(name = "id_materia",referencedColumnName = "id_materia")
    private Materia materia;

    private Double supletorio;
    private Double remedial;
    private Double gracia;
    private Double promedio_final;

    @OneToMany(mappedBy ="detalle",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Quimestre> quimestre;

    @ManyToOne
    @JoinColumn(name = "id_matricula",referencedColumnName = "id_matricula")
    private Matricula matricula;

}
