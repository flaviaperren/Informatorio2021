package com.informatorio.controller;

import com.informatorio.entity.Producto;
import com.informatorio.entity.Categoria;
import com.informatorio.repository.ProductoRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping(value = "/producto/{idProducto}")
    public ResponseEntity<?> getProductoPorId(@PathVariable("idProducto") Long idProducto) {
        return new ResponseEntity<>(productoRepository.findById(idProducto).get(), HttpStatus.OK);
    }
    
    @GetMapping(value = "/producto/nombre")
    public ResponseEntity<?> buscarProductoPorNombre(@RequestParam(name = "nombre",
     required = false) String nombre) {
         return new ResponseEntity<>(productoRepository.findByNombreStartingWith(nombre), HttpStatus.OK);
     }

     @GetMapping(value = "/producto/categoria")
    public ResponseEntity<?> buscarProductoPorCategoria(@RequestParam(name = "categoria",
     required = false) Categoria categoria) {
         return new ResponseEntity<>(productoRepository.findByCategoria(categoria), HttpStatus.OK);
     }

    @PostMapping(value = "/producto")
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto) {
        return new ResponseEntity<>(productoRepository.save(producto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/producto/{idProducto}")
    public ResponseEntity<?> modificarProducto(@PathVariable("idProducto") Long idProducto, @RequestBody Producto producto) {
        Producto productoExistente = productoRepository.findById(idProducto).get();
        productoExistente.setPrecioUnitario(producto.getPrecioUnitario());
        return new ResponseEntity<>(productoRepository.save(productoExistente), HttpStatus.OK);
    }

    @DeleteMapping(value = "/producto/{idProducto}")
    public void borrarPorId(@PathVariable("idProducto") Long idProducto) {
        productoRepository.deleteById(idProducto);
    }

}

