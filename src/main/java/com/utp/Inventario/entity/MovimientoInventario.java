package com.utp.Inventario.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "movimientos_inventario")
public class MovimientoInventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productoId;
    private String nombreProducto;
    private String tipo;
    private Integer cantidad;
    private LocalDateTime fecha;

    public static MovimientoInventario salida(
            Producto p, Integer cantidad) {
        MovimientoInventario m = new MovimientoInventario();
        m.productoId = p.getId();
        m.nombreProducto = p.getNombre();
        m.tipo = "SALIDA";
        m.cantidad = cantidad;
        m.fecha = LocalDateTime.now();
        return m;
    }

    public Long getId() {
        return id;
    }

    public Long getProductoId() {
        return productoId;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    
}
