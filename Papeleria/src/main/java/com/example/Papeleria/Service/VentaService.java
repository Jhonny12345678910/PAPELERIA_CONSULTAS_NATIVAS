package com.example.Papeleria.Service;

import com.example.Papeleria.Model.Venta;
import com.example.Papeleria.Repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    public Optional<Venta> obtenerVentaPorId(Long id) {
        return ventaRepository.findById(id);
    }

    public Venta guardarVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    public void eliminarVenta(Long id) {
        ventaRepository.deleteById(id);
    }

    //Ventas de un empleado
    public List<Venta> listarVentasPorEmpleado(Long idEmpleado){

        List<Venta> ventas = ventaRepository.findByEmpleadoId(idEmpleado);

        return ventas;
    }

    //Ventas de un empleado a un cliente
    public List<Venta> ventasEmpleadoACliente(Long idEmpleado, Long idCliente){

        return ventaRepository.findByEmpleadoIdAndClienteId(idEmpleado, idCliente);
    }

}

