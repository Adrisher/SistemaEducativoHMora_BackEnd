package com.ista.backend.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Entity
@Data
@Table(name = "periodo")
public class Periodo implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_periodo;

    @Column(name="fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fecha_inicio;

    private Date fecha_fin;

    @OneToMany(mappedBy = "periodo", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Matricula> matriculas;
}
