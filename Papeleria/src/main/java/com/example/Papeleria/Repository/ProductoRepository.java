package com.example.Papeleria.Repository;

import com.example.Papeleria.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // 2. Mostrar productos que ofrece un proveedor
    @Query(value = "SELECT * FROM producto WHERE proveedor_id = ?1", nativeQuery = true)
    List<Producto> findByProveedorId(Long proveedorId);
}

