package com.utp.Inventario.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.utp.Inventario.dto.ProductoResumenDTO;
import com.utp.Inventario.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Query("SELECT p FROM Producto p WHERE p.activo = true")
    List<Producto> listarActivos();

    @Query("""
            SELECT p FROM Producto p
            WHERE LOWER(p.nombre)
            LIKE LOWER(CONCAT('%', :texto, '%'))
            """)
    List<Producto> buscarPorNombre(@Param("texto") String texto);

    @Query("""
            SELECT p FROM Producto p
            WHERE p.precio BETWEEN :min AND :max
            """)
    List<Producto> buscarPorPrecio(
            @Param("min") BigDecimal min,
            @Param("max") BigDecimal max);

    @Query("""
            SELECT p
            FROM Producto p
            WHERE p.stock <= :minimo
            ORDER BY p.stock ASC
            """)
    List<Producto> buscarBajoStock(@Param("minimo") Integer minimo);

    @Query("""
            SELECT new com.utp.Inventario.dto.ProductoResumenDTO(
            p.id, p.nombre, p.precio, p.stock)
            FROM Producto p
            WHERE p.activo = true
            ORDER BY p.nombre
            """)
    List<ProductoResumenDTO> listarResumenActivos();
}
