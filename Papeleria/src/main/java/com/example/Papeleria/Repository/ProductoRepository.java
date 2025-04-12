package com.example.Papeleria.Repository;

import com.example.Papeleria.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // 2. Mostrar productos que ofrece un proveedor
    @Query(value = "SELECT * FROM producto WHERE id_proveedor = :idProveedor", nativeQuery = true)
    List<Producto> findByProveedorId(@Param("idProveedor") Long proveedorId);
}

