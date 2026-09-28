package com.utp.Inventario.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.utp.Inventario.dto.ProductoResumenDTO;
import com.utp.Inventario.entity.Producto;
import com.utp.Inventario.service.ProductoService;

@RestController
@RequestMapping ("/productos")
public class ProductoController {
    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto p) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.guardar(p));
    }

    @GetMapping("/{id}")
    public Producto obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @GetMapping
    public List<Producto> listar() {
        return service.listar();
    }

    @GetMapping("/buscar")
    public List<Producto> buscar(@RequestParam String texto) {
        return service.buscarPorNombre(texto);
    }

    @GetMapping("/precio")
    public List<Producto> buscarPorPrecio(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max) {
        return service.buscarPorPrecio(min, max);
    }

    @GetMapping("/bajo-stock")
    public List<Producto> bajoStock(@RequestParam Integer minimo) {
        return service.buscarBajoStock(minimo);
    }

    @GetMapping("/resumen")
    public List<ProductoResumenDTO> obtenerResumen() {
        return service.listarResumenActivos();
    }

}
