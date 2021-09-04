package com.informatorio.controller;

import com.informatorio.entity.Producto;

import java.util.Optional;

import com.informatorio.entity.Categoria;
import com.informatorio.repository.ProductoRepository;
import com.informatorio.service.ProductoService;

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
    public ResponseEntity<?> buscarProductoTodos() {
        return new ResponseEntity<>(productoRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/producto/{idProducto}")
    public ResponseEntity<?> buscarProductoPorId(@PathVariable("idProducto") Long idProducto) {
        Producto producto = productoRepository.findById(idProducto).orElse(null);
        if(producto!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(producto);
        } else {
            return new ResponseEntity<>("No existe el producto buscado", HttpStatus.CONFLICT);
        }  
    }
    
    @GetMapping(value = "/producto/nombre")
    public ResponseEntity<?> buscarProductoPorNombre(@RequestParam(name = "nombre",
     required = false) String nombre) {
         return new ResponseEntity<>(productoRepository.findByNombreContaining(nombre), HttpStatus.OK);
     }

     @GetMapping(value = "/producto/categoria")
    public ResponseEntity<?> buscarProductoPorCategoria(@RequestParam(name = "categoria",
     required = false) Categoria categoria) {
         return new ResponseEntity<>(productoRepository.findByCategoria(categoria), HttpStatus.OK);
     }

     @GetMapping(value = "/producto/noPublicado")
    public ResponseEntity<?> buscarProductoNoPublicado(@RequestParam(name = "publicado",
     required = false) Boolean publicado) {
         return new ResponseEntity<>(productoRepository.findByPublicadoFalse(), HttpStatus.OK);
     }

    @PostMapping(value = "/producto")
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto) {
        Optional<Producto> codigoExistente = productoRepository.findByCodigo(producto.getCodigo());
        if(codigoExistente.isPresent()) {
            return new ResponseEntity<>("Ya existe un producto con el codigo ingresado", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(productoRepository.save(producto), HttpStatus.CREATED);
        }
    }

    @PutMapping(value = "/producto/{idProducto}")
    public ResponseEntity<?> modificarProducto(@PathVariable("idProducto") Long idProducto, 
    @RequestBody Producto productoActualizar) {
        Producto productoExistente = productoRepository.findById(idProducto).get();
        if(productoExistente!=null) {
            ProductoService.modificarProducto(productoExistente, productoActualizar);
        } else {
            return new ResponseEntity<>("No existe el producto solicitado", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(productoRepository.save(productoExistente), HttpStatus.OK);
    }

    @DeleteMapping(value = "/producto/{idProducto}")
    public void borrarProductoPorId(@PathVariable("idProducto") Long idProducto) {
        productoRepository.deleteById(idProducto);
    }

}

