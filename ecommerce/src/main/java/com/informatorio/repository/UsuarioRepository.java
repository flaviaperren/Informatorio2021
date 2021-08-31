package com.informatorio.repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.informatorio.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long>{
    List<Usuario> findByCiudad(String ciudad);
    List<Usuario> findByFechaAltaAfter(LocalDate fechaAlta);
    Optional<Usuario> findByEmail(String email);
}
