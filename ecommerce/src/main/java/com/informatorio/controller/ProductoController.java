package com.informatorio.controller;

import com.informatorio.entity.Producto;
import com.informatorio.repository.ProductoRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;


@RestController
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping(value = "/producto")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(productoRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/producto/{id}")
    public ResponseEntity<?> getProductoPorId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(productoRepository.findById(id).get(), HttpStatus.OK);
    }
    
    @PostMapping(value = "/producto")
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto) {
        return new ResponseEntity<>(productoRepository.save(producto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/producto/{id}")
    public ResponseEntity<?> modificarProducto(@PathVariable("id") Long id, @RequestBody Producto producto) {
        Producto productoExistente = productoRepository.findById(id).get();
        productoExistente.setPrecioUnitario(producto.getPrecioUnitario());
        return new ResponseEntity<>(productoRepository.save(productoExistente), HttpStatus.OK);
    }

    @DeleteMapping(value = "/producto/{id}")
    public void borrarPorId(@PathVariable("id") Long id) {
        productoRepository.deleteById(id);
    }

}

