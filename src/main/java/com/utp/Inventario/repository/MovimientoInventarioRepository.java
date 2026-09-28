package com.utp.Inventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.utp.Inventario.entity.MovimientoInventario;

public interface MovimientoInventarioRepository
        extends JpaRepository<MovimientoInventario, Long> {
    @Query("""
            SELECT m
            FROM MovimientoInventario m
            ORDER BY m.fecha DESC
            """)
    List<MovimientoInventario> listarRecientes();
}
