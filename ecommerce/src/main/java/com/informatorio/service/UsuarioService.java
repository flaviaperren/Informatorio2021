package com.informatorio.service;

import com.informatorio.entity.Usuario;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
   
    public static void modificarUsuario(Usuario usuarioExistente, Usuario usuarioActualizar) {
        usuarioExistente.setDireccion(usuarioActualizar.getDireccion());
        usuarioExistente.setEmail(usuarioActualizar.getEmail());
        usuarioExistente.setPassword(usuarioActualizar.getPassword());
        usuarioExistente.setCiudad(usuarioActualizar.getCiudad());
        usuarioExistente.setProvincia(usuarioActualizar.getProvincia());
        usuarioExistente.setPais(usuarioActualizar.getPais());
        
    } 
}
