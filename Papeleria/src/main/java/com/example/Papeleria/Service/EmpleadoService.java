package com.example.Papeleria.Service;

import com.example.Papeleria.Model.Empleado;
import com.example.Papeleria.Repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> findById(Long id) {
        return empleadoRepository.findById(id);
    }

    public Empleado save(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public Empleado update(Long id, Empleado empleado) {
        Optional<Empleado> existente = empleadoRepository.findById(id);
        if (existente.isPresent()) {
            empleado.setIdEmpleado(id); // Asegúrate de tener este método en tu entidad
            return empleadoRepository.save(empleado);
        } else {
            throw new IllegalArgumentException("Empleado no encontrado");
        }
    }

    public void deleteById(Long id) {
        empleadoRepository.deleteById(id);
    }
}
