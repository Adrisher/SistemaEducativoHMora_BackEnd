package com.ista.school.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ista.school.model.enums.Genero;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "profesor")
public class Profesor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_profesor;

    @Column(name = "cedula", unique = true)
    private String cedula;

    private String nombre;
    private String segundo_nombre;
    private String primer_apellido;
    private String segundo_apellido;
    private String genero;

    @Temporal(TemporalType.DATE)
    private Date fecha_nacimiento;
    private String correo;
    private String direccion;
    private Boolean estado;
    private String area;
    @PrePersist
    public void prePersist() {
        fecha_nacimiento= new Date();
    }

    @PrePersist
    public void prePersist() {
        fecha_nacimiento= new Date();
    }



    @OneToOne
    @JoinColumn(name = "id_usuario",referencedColumnName = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "profesor",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProfesorCursoMateria> profesorCursoMaterias;

}
