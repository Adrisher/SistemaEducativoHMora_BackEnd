package com.ista.school.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "estudiante")
public class Estudiante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_estudiante;

    @Column(name = "cedula",unique = true)
    private String cedula;

    @NotNull(message = "Campo Obligatorio") @NotBlank(message = "No valido")
    private String nombre;
    @NotNull(message = "Campo Obligatorio") @NotBlank(message = "No valido")
    private String segundo_nombre;
    @NotNull(message = "Campo Obligatorio") @NotBlank(message = "No valido")
    private String primer_apellido;
    @NotNull(message = "Campo Obligatorio") @NotBlank(message = "No valido")
    private String segundo_apellido;
    @NotNull(message = "Campo Obligatorio") @NotBlank(message = "No valido")
    @Pattern(regexp = "[MF]{1}")
    private String genero;
    @Temporal(TemporalType.DATE)
    @Past
    private Date fecha_nacimiento;
    @NotNull(message = "Campo Obligatorio") @NotBlank(message = "No valido")
    @Email(message = "Invalid email address")
    private String correo;
    @NotNull(message = "Campo Obligatorio") @NotBlank(message = "No valido")
    private String direccion;
    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean estado;


    @ManyToOne
    @JoinColumn(name = "id_representante",referencedColumnName = "id_representante")
    private Representante representante;

    @OneToMany(mappedBy = "estudiante",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Matricula> matriculas;


    @OneToMany(mappedBy = "estudiante",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Asistencia> asistencia;

    @OneToOne
    @JoinColumn(name = "id_usuario",referencedColumnName = "id_usuario")
    @JsonIgnore
    private Usuario usuario;

}
