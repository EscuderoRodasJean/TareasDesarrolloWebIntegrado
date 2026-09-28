package com.utp.Inventario.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.utp.Inventario.entity.MovimientoInventario;
import com.utp.Inventario.entity.Producto;
import com.utp.Inventario.repository.MovimientoInventarioRepository;
import com.utp.Inventario.repository.ProductoRepository;

@Service
public class InventarioService {
    private final ProductoRepository productoRepository;
    private final MovimientoInventarioRepository movimientoRepository;

    public InventarioService(ProductoRepository productoRepository,
            MovimientoInventarioRepository movimientoRepository) {
        this.productoRepository = productoRepository;
        this.movimientoRepository = movimientoRepository;
    }

    @Transactional
    public MovimientoInventario registrarSalida(
            Long productoId, Integer cantidad) {
        Producto p = productoRepository.findById(productoId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Producto no encontrado"));
        if (p.getStock() < cantidad) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Stock insuficiente");
        }
        p.setStock(p.getStock() - cantidad);
        MovimientoInventario mov = MovimientoInventario.salida(p, cantidad);
        return movimientoRepository.save(mov);
    }

    @Transactional
    public MovimientoInventario salidaConFalla(
            Long productoId, Integer cantidad) {
        registrarSalida(productoId, cantidad);
        throw new IllegalStateException("Falla simulada");
    }

    @Transactional(readOnly = true)
    public List<MovimientoInventario> listarMovimientos() {
        return movimientoRepository.listarRecientes();
    }
}