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
@Table(name = "representante")
public class Representante implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3783564809160280425L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_representante;

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
	private String ocupacion;


	@JsonIgnore
	@OneToMany(mappedBy = "representante",cascade = CascadeType.ALL)
	private List<Estudiante> estudiantes;

}
