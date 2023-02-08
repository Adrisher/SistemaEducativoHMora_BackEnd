package com.ista.backend.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ista.backend.persistence.enums.SexoStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import java.util.List;


@Entity
@Data
@Table(name = "profesor")
public class Profesor implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_profesor;

	@Column(name = "cedula", unique = true)
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
	private String area;
	private String contraseña;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "profesor")
	private List<Materia> materia;

	@JsonIgnore
	@OneToMany(mappedBy = "profesor",cascade = CascadeType.ALL)
	private List<Curso> curso;


}
