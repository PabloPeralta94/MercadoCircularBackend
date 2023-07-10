package com.mercado.circular.service;

import ccom.mercado.circular.exception.UserNotFoundException;
import com.mercado.circular.model.Empleado;
import com.mercado.circular.repository.EmpleadoRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public Empleado addEmpleado(Empleado empleado) {

        // si los nombres concuerdan, no se necesita crear uno por uno los sets
        // pero si se pueden crear sets custom o updatearlos
        empleado.setCodigoEmpleado(UUID.randomUUID().toString());

        return empleadoRepository.save(empleado);
    }

    public List<Empleado> findAllEmpleados() {
        return empleadoRepository.findAll();
    }

    public Empleado findEmpleadoById(Long id) throws Throwable {
        return empleadoRepository.findEmpleadoById(id)
                .orElseThrow(() -> new UserNotFoundException("Empleado con id " + id + " no existe"));
    }

    public Empleado updateEmpleado(Empleado empleado) {
        // recordar, esto se basa en el id del empleado
        return empleadoRepository.save(empleado);
    }

    // No se si es necesario este metodo, porque el deleteById ya existe en JpaRepository
    @Transactional
    public void deleteEmpleado(Long id) {
        empleadoRepository.deleteById(id);
    }

    // create a method that returns a list of employees by id
    public List<Empleado> findEmpleadosById(List<Long> ids) {
        return empleadoRepository.findAllById(ids);
    }

    // también existe el método saveAll en JpaRepository

    // create a method for employ that uses count from jpa repository
    public Long countEmpleados() {
        return empleadoRepository.count();
    }

    // TODO: comprobar si esta query method funciona
    public Long countEmpleadosByNombre(String nombre) {
        return empleadoRepository.countByNombre(nombre);
    }

    // TODO: crear un método que devuelva un empleado por su nombre
    public Empleado findEmpleadoByNombre(String nombre) {
        return empleadoRepository.findEmpleadoByNombre(nombre);
    }

    // TODO: crear un método que devuelva un empleado por su nombre y apellido
    public Empleado findEmpleadoByNombreAndApellido(String nombre, String apellido) {
        return empleadoRepository.findEmpleadoByNombreAndApellido(nombre, apellido);
    }

    // TODO: crear un método que devuelva un empleado por su nombre o apellido
    public Empleado findEmpleadoByNombreOrApellido(String nombre, String apellido) {
        return empleadoRepository.findEmpleadoByNombreOrApellido(nombre, apellido);
    }

    // TODO: crear un método que devuelva una lista de empleados por su nombre
    public List<Empleado> findEmpleadosByNombre(String nombre) {
        return empleadoRepository.findEmpleadosByNombre(nombre);
    }


}
