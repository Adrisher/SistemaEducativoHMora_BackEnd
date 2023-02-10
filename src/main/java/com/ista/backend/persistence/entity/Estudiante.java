package com.ista.backend.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ista.backend.persistence.enums.SexoStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "estudiante")
public class Estudiante implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5450130837865950474L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_estudiante;

	@Column(name = "cedula",unique = true)
	private String cedula;

	private String nombre;
	private String segundo_nombre;
	private String primer_apellido;
	private String segundo_apellido;
	private SexoStatus genero;
	private Date fecha_nacimiento;
	private String correo;
	private String direccion;
	private Boolean estado;


	@ManyToOne
	@JoinColumn(name = "id_representante",referencedColumnName = "id_representante")
	@JsonIgnore
	private Representante representante;

	@OneToMany(mappedBy = "estudiante",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Matricula> matriculas;


	@OneToMany(mappedBy = "estudiante",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Asistencia> asistencia;


}
