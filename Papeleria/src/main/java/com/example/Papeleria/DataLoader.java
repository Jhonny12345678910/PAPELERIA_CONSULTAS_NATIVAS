package com.example.Papeleria;

import com.example.Papeleria.Model.Cliente;
import com.example.Papeleria.Model.Empleado;
import com.example.Papeleria.Model.Proveedor;
import com.example.Papeleria.Model.Producto;
import com.example.Papeleria.Model.Venta;
import com.example.Papeleria.Model.DetalleVenta;

import com.example.Papeleria.Repository.ClienteRepository;
import com.example.Papeleria.Repository.EmpleadoRepository;
import com.example.Papeleria.Repository.ProveedorRepository;
import com.example.Papeleria.Repository.ProductoRepository;
import com.example.Papeleria.Repository.VentaRepository;
import com.example.Papeleria.Repository.DetalleVentaRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final ClienteRepository clienteRepository;
    private final EmpleadoRepository empleadoRepository;
    private final ProveedorRepository proveedorRepository;
    private final ProductoRepository productoRepository;
    private final VentaRepository ventaRepository;
    private final DetalleVentaRepository detalleVentaRepository;

    public DataLoader(ClienteRepository clienteRepository,
                      EmpleadoRepository empleadoRepository,
                      ProveedorRepository proveedorRepository,
                      ProductoRepository productoRepository,
                      VentaRepository ventaRepository,
                      DetalleVentaRepository detalleVentaRepository) {
        this.clienteRepository = clienteRepository;
        this.empleadoRepository = empleadoRepository;
        this.proveedorRepository = proveedorRepository;
        this.productoRepository = productoRepository;
        this.ventaRepository = ventaRepository;
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Override
    public void run(String... args) {
        System.out.println("🚀 Cargando datos de prueba...");

        // Crear clientes
        List<Cliente> clientes = List.of(
                new Cliente(null, "Juan Pérez", "123456789", "3124580943", "juan@gmail.com", null),
                new Cliente(null, "Ana Gómez", "987654321", "3001234567", "ana@gmail.com", null),
                new Cliente(null, "Luis Rodríguez", "456123789", "3119988776", "luis@gmail.com", null),
                new Cliente(null, "Carla Torres", "321654987", "3105566778", "carla@gmail.com", null),
                new Cliente(null, "Sofía Méndez", "741852963", "3011122334", "sofia@gmail.com", null)
        );
        clienteRepository.saveAll(clientes);

        // Crear empleados
        List<Empleado> empleados = List.of(
                new Empleado(null, "María López", "Cajera", "987654321", null),
                new Empleado(null, "Carlos Ruiz", "Vendedor", "112233445", null),
                new Empleado(null, "Laura Jiménez", "Bodeguera", "556677889", null),
                new Empleado(null, "Pedro Salas", "Administrador", "778899001", null),
                new Empleado(null, "Luisa Fernández", "Asistente", "334455667", null)
        );
        empleadoRepository.saveAll(empleados);

        // Crear proveedores
        List<Proveedor> proveedores = List.of(
                new Proveedor(null, "Suministros ABC", "111222333", "abc@proveedor.com", "Av. Principal", null),
                new Proveedor(null, "Papeles XYZ", "444555666", "xyz@papeles.com", "Calle Secundaria", null),
                new Proveedor(null, "OfiExpress", "777888999", "ventas@ofiexpress.com", "Cra 45", null),
                new Proveedor(null, "Distribuciones QWERTY", "121212121", "qwerty@distr.com", "Cl. 100", null),
                new Proveedor(null, "Mayoristas LMN", "101010101", "contacto@lmn.com", "Cra. 10", null)
        );
        proveedorRepository.saveAll(proveedores);

        // Crear productos
        List<Producto> productos = List.of(
                new Producto(null, "Lápiz", "Lápiz HB", 500, 100, proveedores.get(0)),
                new Producto(null, "Cuaderno", "Cuaderno rayado", 12000, 50, proveedores.get(1)),
                new Producto(null, "Borrador", "Borrador blanco", 400, 200, proveedores.get(2)),
                new Producto(null, "Regla", "Regla 30cm", 800, 75, proveedores.get(3)),
                new Producto(null, "Marcador", "Marcador permanente", 1500, 60, proveedores.get(4))
        );
        productoRepository.saveAll(productos);

        // Crear ventas con detalles
        for (int i = 0; i < 5; i++) {
            Cliente cliente = clientes.get(i);
            Empleado empleado = empleados.get(i);
            Producto producto = productos.get(i);

            Venta venta = new Venta(null, new Date(System.currentTimeMillis()), cliente, empleado, null);
            ventaRepository.save(venta);

            DetalleVenta detalle = new DetalleVenta(null, 2, producto.getPrecio(), 2 * producto.getPrecio(), venta, producto);
            detalleVentaRepository.save(detalle);

            List<DetalleVenta> detalles = new ArrayList<>();
            detalles.add(detalle);
            venta.setDetalles(detalles);
            ventaRepository.save(venta);
        }

        System.out.println("✅ Datos de prueba insertados correctamente.");
    }
}
