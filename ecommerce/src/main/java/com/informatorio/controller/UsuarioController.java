package com.informatorio.controller;

import java.time.LocalDate;

import java.util.Optional;

import com.informatorio.entity.Usuario;
import com.informatorio.repository.UsuarioRepository;
import com.informatorio.service.UsuarioService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(value = "/usuario")
    public ResponseEntity<?> buscarUsuarioTodos() {
        return new ResponseEntity<>(usuarioRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/usuario/{idUsuario}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable("idUsuario") Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null); 
            if(usuario!=null) {
                return ResponseEntity.status(HttpStatus.OK).body(usuario);
            } else {
                return new ResponseEntity<>("No existe el usuario buscado", HttpStatus.NOT_FOUND);
            }
    }

    @GetMapping(value = "/usuario/ciudad")
    public ResponseEntity<?> buscarUsuarioPorCiudad(@RequestParam(name = "ciudad",
     required = false) String ciudad) {
        return new ResponseEntity<>(usuarioRepository.findByCiudad(ciudad), HttpStatus.OK);
    }
      
     @GetMapping(value = "/usuario/fecha")
     public ResponseEntity<?> buscarUsuarioPorFecha(@RequestParam(name = "fechaAlta", required = false) 
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaAlta) {
        return new ResponseEntity<>(usuarioRepository.findByFechaAltaAfter(fechaAlta), HttpStatus.OK); 
     }
    
    @PostMapping(value = "/usuario")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        Optional<Usuario> correoExistente = usuarioRepository.findByEmail(usuario.getEmail());
        if(correoExistente.isPresent()) {
            return new ResponseEntity<>("Ya existe un usuario con el email ingresado", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.CREATED);
        } 
    }

    @PutMapping(value = "/usuario/{idUsuario}")
    public ResponseEntity<?> modificarUsuario(@PathVariable("idUsuario") Long idUsuario, 
    @RequestBody Usuario usuarioActualizar) {
        Usuario usuarioExistente = usuarioRepository.findById(idUsuario).orElse(null);
        if(usuarioExistente!=null) {
            UsuarioService.modificarUsuario(usuarioExistente, usuarioActualizar);
        } else {
            return new ResponseEntity<>("No existe el usuario solicitado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuarioRepository.save(usuarioExistente), HttpStatus.OK);
    }

    @DeleteMapping(value = "/usuario/{idUsuario}")
    public void borrarUsuarioPorId(@PathVariable("idUsuario") Long idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }


    
}
