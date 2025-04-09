package com.example.Papeleria.Repository;


import com.example.Papeleria.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}

