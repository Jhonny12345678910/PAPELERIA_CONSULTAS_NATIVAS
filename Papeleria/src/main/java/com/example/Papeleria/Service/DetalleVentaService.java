package com.example.Papeleria.Service;

import com.example.Papeleria.Model.DetalleVenta;
import com.example.Papeleria.Model.Venta;
import com.example.Papeleria.Repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    public List<DetalleVenta> findAll() {
        return detalleVentaRepository.findAll();
    }

    public Optional<DetalleVenta> findById(Long id) {
        return detalleVentaRepository.findById(id);
    }

    public DetalleVenta save(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }

    public DetalleVenta update(Long id, DetalleVenta detalleVenta) {
        Optional<DetalleVenta> existente = detalleVentaRepository.findById(id);
        if (existente.isPresent()) {
            detalleVenta.setIdDetalleVenta(id); // Asegúrate de tener un método `setId` en tu entidad `DetalleVenta`
            return detalleVentaRepository.save(detalleVenta);
        } else {
            throw new IllegalArgumentException("DetalleVenta no encontrado");
        }
    }

    public void deleteById(Long id) {
        detalleVentaRepository.deleteById(id);
    }

    //Ventas de un empleado a un cliente
    public List<DetalleVenta> detalleVentasEmpleadoACliente(Long idEmpleado, Long idCliente){

        return detalleVentaRepository.detallesVentasEmpleadoCliente(idEmpleado, idCliente);
    }
}
