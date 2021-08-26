package com.informatorio.repository;

import com.informatorio.entity.DetalleCarrito;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleCarritoRepository extends JpaRepository<DetalleCarrito, Long>{
    DetalleCarrito getById(Long idDetalleCarrito);
}
