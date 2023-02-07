package com.ista.backend.service.dto;

import com.ista.backend.persistence.enums.SexoStatus;


import java.util.Date;
import lombok.Data;


@Data
public class ProfesorDTO {

    private String cedula;
    private String nombre;
    private String segundo_nombre;
    private String primer_apellido;
    private String segundo_apellido;
    private SexoStatus genero;
    private Date fecha_nacimiento;
    private String correo;
    private String direccion;
    private String area;
    private String contraseña;
}
