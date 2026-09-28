package com.utp.Inventario.dto;

import java.math.BigDecimal;

public record ProductoResumenDTO(
        Long id,
        String nombre,
        BigDecimal precio,
        Integer stock) {
}