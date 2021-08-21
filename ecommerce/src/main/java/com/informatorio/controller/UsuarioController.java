package com.informatorio.controller;

import com.informatorio.entity.Usuario;
import com.informatorio.repository.UsuarioRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(value = "/usuario")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(usuarioRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity<?> getUsuarioPorId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(usuarioRepository.findById(id).get(), HttpStatus.OK);
    }
    
    @PostMapping(value = "/usuario")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

    @PutMapping(value = "/usuario/{id}")
    public ResponseEntity<?> modificarUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findById(id).get();
        usuarioExistente.setDireccion(usuario.getDireccion());
        return new ResponseEntity<>(usuarioRepository.save(usuarioExistente), HttpStatus.OK);
    }

    @DeleteMapping(value = "/usuario/{id}")
    public void borrarPorId(@PathVariable("id") Long id) {
        usuarioRepository.deleteById(id);
    }


    
}
