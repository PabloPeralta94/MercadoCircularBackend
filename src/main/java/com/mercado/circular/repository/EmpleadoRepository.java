package com.mercado.circular.repository;

import com.mercado.circular.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    // Query method ↓
    void deleteEmpleadoById(Long id);

    // Query method ↓
    //Empleado findEmpleadoById(Long id);

    Optional<Empleado> findEmpleadoById(Long id);

    Long countByNombre(String nombre);

    Empleado findEmpleadoByNombre(String nombre);

    Empleado findEmpleadoByNombreAndApellido(String nombre, String apellido);

    Empleado findEmpleadoByNombreOrApellido(String nombre, String apellido);

    List<Empleado> findEmpleadosByNombre(String nombre);

    // Revisar la clase JpaRepository, porque por ejemplo getOne y getById estan deprecados
    // y dice de usar getReferenceById

    // @Deprecated
    //    T getOne(ID id);
    //
    //    /** @deprecated */
    //    @Deprecated
    //    T getById(ID id);
    //
    //    T getReferenceById(ID id);
    //
    //    <S extends T> List<S> findAll(Example<S> example);
    //
    //    <S extends T> List<S> findAll(Example<S> example, Sort sort);
    //}
}