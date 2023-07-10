package com.mercado.circular.model;

import javax.persistence.*;

import lombok.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
//@Builder(toBuilder = true)

@Entity
public class Empleado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // una vez asignado no puede cambiarse el id
    @Column(nullable = false, updatable = false)
    private Long id;

    private String nombre;
    private String apellido;
    private String email;
    private String telefono;

    private String puestoDeTrabajo;
    private String imageUrl;
    private String codigoEmpleado;

    /*private String pais;
    private String provincia;
    private String ciudad;
    private String codigoPostal;
    private String direccion;*/

    /*private String fechaNacimiento;
    private String fechaContratacion;
    private String titulo;
    private String notas;
    private String foto;*/

//    salario (salary)
//    departamento (department)
//    numeroEmpleado (employee number)
//    estadoEmpleado (employee status)
//    numeroSeguridadSocial (social security number)
//    habilidades (skills)
//    certificaciones (certifications)
//    idiomas (languages)
//    experienciaLaboral (work experience)
//    educacion (education)
//    genero (gender)
//    estadoCivil (marital status)
//    numeroHijos (number of children)
//    tipoEmpleado (employee type)
//    horarioTrabajo (work schedule)
//    vacacionesDisponibles (available vacation days)
//    permisosDisponibles (available permissions)
//    afiliaciones (affiliations)
//    beneficios (benefits)
//    seguroMedico (health insurance)
//    401k o plan de jubilacion (401k or retirement plan)
//    contrato (contract)
//    jefes (managers)
//    colaboradores (coworkers)
//    proyectos (projects)
//    tareas (tasks)
//    horasTrabajadas (hours worked)
//    horasExtra (overtime hours)
//    díasFaltantes (absent days)
//    díasVacaciones (vacation days)
//    díasPermiso (permission days)
//    díasEnfermedad (sick days)
//    ausenciasJustificadas (justified absences)
//    ausenciasNoJustificadas (unjustified absences)
//    salarioBase (base salary)
//    bonos (bonuses)
//    deducciones (deductions)
//    salarioBruto (gross salary)
//    salarioNeto (net salary)
//    impuestos (taxes)
//    beneficiosAdicionales (additional benefits)
//    deduccionesAdicionales (additional deductions)
//    salarioFinal (final salary)
//    fechaFinalContrato (contract end date)
//    motivoFinalContrato (contract end reason)
//    fechaFinalizacion (end date)
//    motivoFinalizacion (end reason)
//    observaciones (notes)
//    archivosAdjuntos (attachments)
//    sistemasAcceso (access systems)
//    roles (roles)
//    permisos (permissions)
//    historialCambios (change history)
//    fechaCreacion (creation date)
//    usuarioCreacion (creation user)
//    fechaModificacion (modification date)
//    usuarioModificacion (modification user)
//    activo (active)
//    borrado (deleted)
//    version (version)
}
