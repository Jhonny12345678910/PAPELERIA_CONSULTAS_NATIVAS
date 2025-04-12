package com.example.Papeleria.Repository;

import com.example.Papeleria.Model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {

    // 4. Mostrar detalles de venta hechos por un empleado a un cliente
    @Query(value = """
    SELECT dv.* FROM detalle_venta dv
    JOIN venta v ON dv.venta_id = v.id_venta
    WHERE v.id_empleado = :idEmpleado AND v.id_cliente = :idCliente
    """, nativeQuery = true)
    List<DetalleVenta> detallesVentasEmpleadoCliente(
            @Param("idEmpleado") Long idEmpleado,
            @Param("idCliente") Long idCliente);
}

