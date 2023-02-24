package com.ista.school.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "periodo")
public class Periodo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_periodo;

    @Column(name="fecha_inicio",updatable = false)
    @CreationTimestamp
    private Date fecha_inicio;

    @Column(name = "fecha_fin",insertable = false)
    @UpdateTimestamp
    private Date fecha_fin;

    @OneToMany(mappedBy = "periodo", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Matricula> matriculas;

}
