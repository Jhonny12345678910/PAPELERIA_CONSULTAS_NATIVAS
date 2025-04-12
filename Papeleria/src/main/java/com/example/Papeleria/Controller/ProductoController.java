package com.example.Papeleria.Controller;

import com.example.Papeleria.Model.Producto;
import com.example.Papeleria.Model.Venta;
import com.example.Papeleria.Service.ProductoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        return ResponseEntity.ok(productoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        return productoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<?> createProducto(@RequestBody Producto producto) {
        try {
            Producto nuevo = productoService.save(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        try {
            Producto actualizado = productoService.update(id, producto);
            return ResponseEntity.ok(actualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProducto(@PathVariable Long id) {
        try {
            productoService.deleteById(id);
            return ResponseEntity.ok("Producto eliminado correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado.");
        }
    }

    @GetMapping("/proveedor/{idProveedor}")
    public ResponseEntity<List<Producto>> findByProveedor(@PathVariable Long idProveedor) {
        try {
            return ResponseEntity.ok(productoService.listarProductosPorProveedor(idProveedor));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of()); // o null si prefieres
        }
    }
}
