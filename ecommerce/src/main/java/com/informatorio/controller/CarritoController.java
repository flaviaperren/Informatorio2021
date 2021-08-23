package com.informatorio.controller;

import javax.validation.Valid;

import com.informatorio.entity.Carrito;
import com.informatorio.entity.Usuario;
import com.informatorio.repository.CarritoRepository;
import com.informatorio.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarritoController {
    @Autowired
    private CarritoRepository carritoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(value = "/carrito")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(carritoRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/carrito/{idCarrito}") 
    public ResponseEntity<?> getCarritoPorId(@PathVariable("idCarrito") Long idCarrito) {
        return new ResponseEntity<>(carritoRepository.findById(idCarrito).get(), HttpStatus.OK);
    }

    @PostMapping(value = "/usuario/{idUsuario}/carrito")
    public ResponseEntity<?> crearCarrito(@PathVariable("idUsuario") Long idUsuario, 
    @Valid @RequestBody Carrito carrito) {
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        carrito.setUsuario(usuario);
        return new ResponseEntity<>(carritoRepository.save(carrito), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/usuario/{idUsuario}/carrito/{idCarrito}")
    public void borrarPorId(@PathVariable("idUsuario") Long idUsuario,
    @PathVariable("idCarrito") Long idCarrito) {
        carritoRepository.deleteById(idCarrito);
    }
}
