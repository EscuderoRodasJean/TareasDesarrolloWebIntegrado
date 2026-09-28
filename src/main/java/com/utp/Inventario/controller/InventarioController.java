package com.utp.Inventario.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.utp.Inventario.entity.MovimientoInventario;
import com.utp.Inventario.service.InventarioService;

@RestController
@RequestMapping("/inventario")
public class InventarioController {
    private final InventarioService service;

    public InventarioController(InventarioService service) {
        this.service = service;
    }

    
    @PostMapping("/salida")
    public MovimientoInventario registrarSalida(
            @RequestParam Long productoId,
            @RequestParam Integer cantidad) {
        return service.registrarSalida(productoId, cantidad);
    }

   
    @PostMapping("/salida-falla")
    public MovimientoInventario salidaConFalla(
            @RequestParam Long productoId,
            @RequestParam Integer cantidad) {
        return service.salidaConFalla(productoId, cantidad);
    }

   
    @GetMapping("/movimientos")
    public List<MovimientoInventario> movimientos() {
        return service.listarMovimientos();
    }
}