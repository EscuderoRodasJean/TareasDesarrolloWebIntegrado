package com.utp.Inventario.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.utp.Inventario.dto.ProductoResumenDTO;
import com.utp.Inventario.entity.Producto;
import com.utp.Inventario.repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public List<Producto> listar() {
        return repository.findAll();
    }

    public Producto guardar(Producto producto) {
        return repository.save(producto);
    }

    public Producto actualizar(Long id, Producto datos) {
        Producto actual = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Producto no encontrado"));
        actual.setNombre(datos.getNombre());
        actual.setPrecio(datos.getPrecio());
        actual.setStock(datos.getStock());
        actual.setActivo(datos.getActivo());
        return repository.save(actual);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
        repository.deleteById(id);
    }

    public Producto obtener(Long id) { 
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Producto no encontrado"));
    }

    @Transactional(readOnly = true)
    public List<Producto> buscarPorNombre(String texto) {
        return repository.buscarPorNombre(texto);
    }

    @Transactional(readOnly = true)
    public List<Producto> buscarPorPrecio(BigDecimal min, BigDecimal max) {
        return repository.buscarPorPrecio(min, max);
    }

    @Transactional(readOnly = true)
    public List<Producto> buscarBajoStock(Integer minimo) {
        return repository.buscarBajoStock(minimo);
    }

    @Transactional(readOnly = true)
    public List<ProductoResumenDTO> listarResumenActivos() {
        return repository.listarResumenActivos();
    }
}
