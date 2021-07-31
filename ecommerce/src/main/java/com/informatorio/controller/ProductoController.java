package com.informatorio.controller;

import com.informatorio.entity.Producto;
import com.informatorio.repository.ProductoRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;

    @RequestMapping(value = "/producto", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        return new ResponseEntity(productoRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/producto/{id}", method = RequestMethod.GET)
    public Producto getProductoPorId(@PathVariable("id") Long id) {
        return productoRepository.findById(id).get();
    }
    
    @RequestMapping(value = "/producto", method = RequestMethod.POST)
    public Producto createProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @RequestMapping(value = "/producto/{id}", method = RequestMethod.PUT)
    public Producto modificarProducto(@PathVariable("id") Long id, @RequestBody Producto producto) {
        Producto productoExistente = productoRepository.findById(id).get();
        productoExistente.setPrecioUnitario(producto.getPrecioUnitario());
        return productoRepository.save(productoExistente);
    }

    @RequestMapping(value = "/producto/{id}", method = RequestMethod.DELETE)
    public void borrarPorId(@PathVariable("id") Long id) {
        productoRepository.deleteById(id);
    }

}

