package com.example.Papeleria.Repository;

import com.example.Papeleria.Model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    // 1. Listar ventas de un empleado
    @Query(value = "SELECT * FROM venta WHERE empleado_id = ?1", nativeQuery = true)
    List<Venta> findByEmpleadoId(Long empleadoId);

    // 3. Ventas que le ha realizado un empleado a un cliente
    @Query(value = "SELECT * FROM venta WHERE empleado_id = ?1 AND cliente_id = ?2", nativeQuery = true)
    List<Venta> findByEmpleadoIdAndClienteId(Long empleadoId, Long clienteId);
}