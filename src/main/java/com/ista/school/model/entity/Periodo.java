package com.ista.school.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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
    @NotNull(message = "Campo Obligatorio")
    @Temporal(TemporalType.DATE)
    private Date fecha_inicio;
    @NotNull(message = "Campo Obligatorio")
    @Temporal(TemporalType.DATE)
    private Date fecha_fin;

    @OneToMany(mappedBy = "periodo", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Matricula> matriculas;

}
