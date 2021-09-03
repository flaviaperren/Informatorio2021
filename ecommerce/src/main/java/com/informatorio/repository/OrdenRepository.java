package com.informatorio.repository;

import java.util.List;

import com.informatorio.entity.Orden;
import com.informatorio.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long>{
    List<Orden> findByUsuario(Usuario usuario);
    Orden getById(Long idOrden);
}
