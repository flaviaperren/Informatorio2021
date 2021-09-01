package com.informatorio.repository;
import java.util.List;
import java.util.Optional;

import com.informatorio.entity.Producto;
import com.informatorio.entity.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository <Producto, Long> {
    List<Producto> findByNombreContaining(String nombre);
    List<Producto> findByCategoria(Categoria categoria);
    Optional<Producto> findByCodigo(String codigo);
    List<Producto> findByPublicadoFalse();
}
