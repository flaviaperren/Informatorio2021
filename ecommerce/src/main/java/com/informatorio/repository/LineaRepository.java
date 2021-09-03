package com.informatorio.repository;

import com.informatorio.entity.Linea;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LineaRepository extends JpaRepository<Linea, Long>{
    
}
