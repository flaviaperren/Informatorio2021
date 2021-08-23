package com.informatorio.repository;

import com.informatorio.entity.Carrito;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long>{
    
}
