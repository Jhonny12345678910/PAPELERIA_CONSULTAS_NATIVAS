package com.example.Papeleria.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String cedula;
    private String telefono;
    private String correo;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Venta> ventas;

    // Constructor vacío
    public Cliente() {
    }

    // Constructor con todos los campos (por si lo necesitas más adelante)
    public Cliente(Long id, String nombre, String cedula, String telefono, String correo, List<Venta> ventas) {
        this.id = id;
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.correo = correo;
        this.ventas = ventas;
    }

    // Constructor útil para cargar datos fácilmente
    public Cliente(String nombre, String cedula, String telefono, String correo) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.correo = correo;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }
}
