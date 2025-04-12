package com.example.Papeleria.Service;

import com.example.Papeleria.Model.Producto;
import com.example.Papeleria.Model.Venta;
import com.example.Papeleria.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id);
    }

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto update(Long id, Producto producto) {
        Optional<Producto> existente = productoRepository.findById(id);
        if (existente.isPresent()) {
            producto.setIdProducto(id); // Asegúrate de tener este setter en tu entidad Producto
            return productoRepository.save(producto);
        } else {
            throw new IllegalArgumentException("Producto no encontrado");
        }
    }

    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

    //Ventas de un empleado
    public List<Producto> listarProductosPorProveedor(Long idProveedor){

        List<Producto> productos = productoRepository.findByProveedorId(idProveedor);

        return productos;
    }
}
