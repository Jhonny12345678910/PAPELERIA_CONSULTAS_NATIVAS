package com.example.Papeleria.Controller;

import com.example.Papeleria.Model.DetalleVenta;
import com.example.Papeleria.Service.DetalleVentaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-venta")
@CrossOrigin(origins = "*")
public class DetalleVentaController {

    private final DetalleVentaService service;

    public DetalleVentaController(DetalleVentaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DetalleVenta>> getAllDetalles() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVenta> getDetalleById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<?> createDetalle(@RequestBody DetalleVenta detalleVenta) {
        try {
            DetalleVenta nuevo = service.save(detalleVenta);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDetalle(@PathVariable Long id, @RequestBody DetalleVenta detalleVenta) {
        try {
            // Este método lo tienes que implementar si quieres hacer actualizaciones.
            // Por ahora mandamos un 501 Not Implemented
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Actualización aún no implementada.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDetalle(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.ok("Detalle eliminado correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Detalle no encontrado.");
        }
    }
}
