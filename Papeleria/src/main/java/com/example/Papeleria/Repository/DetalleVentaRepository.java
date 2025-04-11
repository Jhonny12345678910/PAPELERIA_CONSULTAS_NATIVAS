package com.example.Papeleria.Repository;

import com.example.Papeleria.Model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {

    // 4. Mostrar detalles de venta hechos por un empleado a un cliente
    @Query(value = "SELECT dv.* FROM detalle_venta dv " +
            "JOIN venta v ON dv.venta_id = v.id " +
            "WHERE v.empleado_id = ?1 AND v.cliente_id = ?2", nativeQuery = true)
    List<DetalleVenta> findDetallesByEmpleadoAndCliente(Long empleadoId, Long clienteId);
}

