package com.informatorio.service;

import com.informatorio.entity.Producto;

import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    public static void modificarProducto(Producto productoExistente, Producto productoActualizar) {
        productoExistente.setDescripcion(productoActualizar.getDescripcion());
        productoExistente.setContenido(productoActualizar.getContenido());
        productoExistente.setPublicado(productoActualizar.getPublicado());
        productoExistente.setPrecioUnitario(productoActualizar.getPrecioUnitario());
        productoExistente.setCategoria(productoActualizar.getCategoria());
    } 
}
