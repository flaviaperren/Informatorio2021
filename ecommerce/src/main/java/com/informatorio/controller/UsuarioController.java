package com.informatorio.controller;

import com.informatorio.entity.Usuario;
import com.informatorio.repository.UsuarioRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @RequestMapping(value = "/usuario", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        return new ResponseEntity(usuarioRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.GET)
    public Usuario getUsuarioPorId(@PathVariable("id") Long id) {
        return usuarioRepository.findById(id).get();
    }
    
    @RequestMapping(value = "/usuario", method = RequestMethod.POST)
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.PUT)
    public Usuario modificarUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findById(id).get();
        usuarioExistente.setDireccion(usuario.getDireccion());
        return usuarioRepository.save(usuarioExistente);
    }

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.DELETE)
    public void borrarPorId(@PathVariable("id") Long id) {
        usuarioRepository.deleteById(id);
    }


    
}
