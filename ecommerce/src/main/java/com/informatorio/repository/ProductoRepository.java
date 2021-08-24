package com.informatorio.repository;
import java.util.List;

import com.informatorio.entity.Producto;
import com.informatorio.entity.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository <Producto, Long> {
    List<Producto> findByNombreStartingWith(String nombre);
    List<Producto> findByCategoria(Categoria categoria);
}
