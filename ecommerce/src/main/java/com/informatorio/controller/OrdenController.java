package com.informatorio.controller;

import java.util.List;

import com.informatorio.entity.Orden;
import com.informatorio.entity.Usuario;
import com.informatorio.repository.OrdenRepository;
import com.informatorio.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdenController {
    @Autowired
    private OrdenRepository ordenRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(value = "/orden")
        public ResponseEntity<?> buscarOrdenTodas() {
            return new ResponseEntity<>(ordenRepository.findAll(), HttpStatus.OK);
    }
    
    @GetMapping(value = "/orden/{idOrden}")
    public ResponseEntity<?> buscarOrdenPorId(@PathVariable("idOrden") Long idOrden) {
        Orden orden = ordenRepository.findById(idOrden).orElse(null);
        if(orden!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(orden);
        } else {
            return new ResponseEntity<>("No existe la orden buscada", HttpStatus.CONFLICT);
        }
    }
     
    @GetMapping(value = "/usuario/{idUsuario}/orden")
        public List<Orden> buscarOrdenPorUsuario(@PathVariable("idUsuario") Long idUsuario) {
            Usuario usuario = usuarioRepository.findById(idUsuario).get();
           return (ordenRepository.findByUsuario(usuario)); 
        }
}
