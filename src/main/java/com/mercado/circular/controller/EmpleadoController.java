package com.mercado.circular.controller;

import com.mercado.circular.model.Empleado;
import com.mercado.circular.service.EmpleadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/empleados")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }


    @GetMapping // getAllEmpleados (1)
    public ResponseEntity<List<Empleado>> getAllEmpleados() {
        List<Empleado> empleados = empleadoService.findAllEmpleados();
        return new ResponseEntity(empleados, HttpStatus.OK);
    }

    @GetMapping("/find/{empleadoId}") // (2)
    public ResponseEntity<Empleado> getEmpleadoById(
            @PathVariable("empleadoId") Long empleadoId) throws Throwable {
        Empleado empleado = empleadoService.findEmpleadoById(empleadoId);
        return new ResponseEntity(empleado, HttpStatus.OK);
    }

    @PostMapping("/add") // (3)
    public ResponseEntity<Empleado> addEmpleado(@RequestBody Empleado empleado) {
        Empleado newEmpleado = empleadoService.addEmpleado(empleado);
        return new ResponseEntity(newEmpleado, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update") // (4)
    public ResponseEntity<Empleado> updateEmpleado(@RequestBody Empleado empleado) {
        Empleado updatedEmpleado = empleadoService.updateEmpleado(empleado);
        return new ResponseEntity(updatedEmpleado, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{empleadoId}") // (5)
    public ResponseEntity<?> deleteEmpleado(@PathVariable("empleadoId") Long empleadoId) {
        empleadoService.deleteEmpleado(empleadoId);
        return new ResponseEntity(HttpStatus.OK);
    }
}