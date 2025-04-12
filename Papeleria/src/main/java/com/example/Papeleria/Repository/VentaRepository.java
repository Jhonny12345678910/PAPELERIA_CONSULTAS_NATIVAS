package com.example.Papeleria.Repository;

import com.example.Papeleria.Model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    // 1. Listar ventas de un empleado
    @Query(value = "SELECT * FROM venta WHERE id_empleado = :idEmpleado", nativeQuery = true)
    List<Venta> findByEmpleadoId(@Param("idEmpleado") Long empleadoId);

    @Query(value = "SELECT * FROM venta WHERE id_empleado = :idEmpleado AND id_cliente = :idCliente", nativeQuery = true)
    List<Venta> findByEmpleadoIdAndClienteId(@Param("idEmpleado") Long idEmpleado, @Param("idCliente") Long idCliente);

}