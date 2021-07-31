package com.informatorio.repository;
import com.informatorio.entity.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends CrudRepository <Producto, Long> {
    
}
