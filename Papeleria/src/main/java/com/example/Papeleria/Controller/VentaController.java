package com.example.Papeleria.Controller;

import com.example.Papeleria.Model.Venta;
import com.example.Papeleria.Service.VentaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")//http://localhost:8080/api/ventas/empleado/2/cliente/2
@CrossOrigin(origins = "*")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public ResponseEntity<List<Venta>> getAllVentas() {
        return ResponseEntity.ok(ventaService.listarVentas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVentaById(@PathVariable Long id) {
        return ventaService.obtenerVentaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<?> createVenta(@RequestBody Venta venta) {
        try {
            Venta nuevaVenta = ventaService.guardarVenta(venta);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVenta(@PathVariable Long id) {
        try {
            ventaService.eliminarVenta(id);
            return ResponseEntity.ok("Venta eliminada correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venta no encontrada.");
        }
    }

    @GetMapping("/empleado/{idEmpleado}")
    public ResponseEntity<List<Venta>> findByEmpleado(@PathVariable Long idEmpleado) {
        try {
            return ResponseEntity.ok(ventaService.listarVentasPorEmpleado(idEmpleado));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of()); // o null si prefieres
        }
    }
    @GetMapping("/empleado/{idEmpleado}/cliente/{idCliente}")
    public ResponseEntity<List<Venta>> findByEmpleadoAndCliente(@PathVariable Long idEmpleado, @PathVariable Long idCliente) {
        try {
            return ResponseEntity.ok(ventaService.ventasEmpleadoACliente(idEmpleado, idCliente));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of()); // o null si prefieres
        }
    }


}



