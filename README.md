# Proyecto Papelería - Consultas y Swagger

**Autor:** Jhonattan Pineda Cardona

---

## ¿Cómo funcionan las peticiones en Swagger?

Swagger es una interfaz web automática que nos permite observar los controladores allí cargados. Se genera con la dependencia `springdoc-openapi`.

Esta interfaz permite probar de manera visual las operaciones CRUD (GET, POST, PUT, DELETE) sobre nuestras entidades, de forma similar a Postman.

Cada petición se puede ejecutar directamente desde la interfaz, rellenando los datos solicitados en el formulario que se muestra al expandir cada endpoint. Swagger se conecta automáticamente a nuestros controladores (`@RestController`) y genera la documentación.

---

## Consultas Nativas

A continuación, se describen cuatro consultas nativas implementadas en el proyecto:

### 1) Listar ventas de un empleado

Hace una petición y el sistema busca todas las filas de la tabla `Venta`, en donde el campo `empleado_id` coincida con el valor `2`.

**GET** `http://localhost:8080/api/ventas/empleado/2`

```json
[
  {
    "idVenta": 2,
    "fecha": "2025-04-09",
    "cliente": {
      "id": 2,
      "nombre": "Juan Pérez",
      "cedula": "123456789",
      "telefono": "3124580943",
      "correo": "juan@gmail.com"
    },
    "empleado": {
      "idEmpleado": 2,
      "nombre": "María López",
      "cargo": "Cajera",
      "telefono": "987654321"
    }
  }
]
```
### 2) Mostrar productos que ofrece un proveedor

El sistema realiza una búsqueda en la tabla `Producto`, filtrando los registros en donde el campo `proveedor_id` coincida con 2.

**GET** `http://localhost:8080/api/productos/proveedor/2`

```json 
[
{
"idProducto": 2,
"nombre": "Lápiz",
"descripcion": "Lápiz HB",
"precio": 0.5,
"stock": 100,
"proveedor": {
"idProveedor": 2,
"nombre": "Suministros ABC",
"telefono": "111222333",
"correo": "abc@proveedor.com",
"direccion": "Av. Principal"
}
}
]
```

### 3) Ventas que le ha realizado un empleado a un cliente
   El sistema consulta la tabla `Venta`, buscando coincidencias donde `empleado_id` y `cliente_id` sean ambos iguales a 2. Esto permite filtrar las ventas por empleado y cliente específicos.

** GET**  `http://localhost:8080/api/ventas/empleado/2/cliente/2`

```json
[
  {
    "idVenta": 2,
    "fecha": "2025-04-09",
    "cliente": {
      "id": 2,
      "nombre": "Juan Pérez",
      "cedula": "123456789",
      "telefono": "3124580943",
      "correo": "juan@gmail.com"
    },
    "empleado": {
      "idEmpleado": 2,
      "nombre": "María López",
      "cargo": "Cajera",
      "telefono": "987654321"
    }
  }
]
```

### 4) Mostrar detalles de venta hechos por un empleado a un cliente
   El sistema recorre las ventas realizadas por el empleado al cliente, y por cada venta obtiene los registros de la tabla `DetalleVenta` con sus respectivos detalles.

**GET** `http://localhost:8080/api/detalles-venta/empleado/2/cliente/2`

```json
[
  {
    "idDetalleVenta": 2,
    "cantidad": 2,
    "precioUnitario": 0.5,
    "subtotal": 1.0,
    "venta": {
      "idVenta": 2,
      "fecha": "2025-04-09",
      "cliente": {
        "id": 2,
        "nombre": "Juan Pérez",
        "cedula": "123456789",
        "telefono": "3124580943",
        "correo": "juan@gmail.com"
      },
      "empleado": {
        "idEmpleado": 2,
        "nombre": "María López",
        "cargo": "Cajera",
        "telefono": "987654321"
      }
    },
    "producto": {
      "idProducto": 2,
      "nombre": "Lápiz",
      "descripcion": "Lápiz HB",
      "precio": 0.5,
      "stock": 100,
      "proveedor": {
        "idProveedor": 2,
        "nombre": "Suministros ABC",
        "telefono": "111222333",
        "correo": "abc@proveedor.com",
        "direccion": "Av. Principal"
      }
    }
  }
]
```
