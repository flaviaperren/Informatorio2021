package com.informatorio.controller;

import javax.validation.Valid;

import com.informatorio.entity.Carrito;
import com.informatorio.entity.DetalleCarrito;
import com.informatorio.entity.Producto;
import com.informatorio.entity.Usuario;
import com.informatorio.repository.CarritoRepository;
import com.informatorio.repository.DetalleCarritoRepository;
import com.informatorio.repository.ProductoRepository;
import com.informatorio.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarritoController {
    @Autowired
    private CarritoRepository carritoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired 
    private ProductoRepository productoRepository;
    @Autowired
    private DetalleCarritoRepository detalleCarritoRepository;
 
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

    @PutMapping(value = "/productoMas/{idProducto}/carrito/{idCarrito}")
    public ResponseEntity<?> agregarProducto(@PathVariable("idProducto") Long idProducto,
    @PathVariable("idCarrito") Long idCarrito,
    @Valid @RequestBody DetalleCarrito detalleCarrito) {
        Carrito carritoExistente = carritoRepository.getById(idCarrito);
        Producto productoAgregar = productoRepository.getById(idProducto);
        DetalleCarrito detalle = new DetalleCarrito();
        detalle.setCarrito(carritoExistente);
        detalle.setProducto(productoAgregar);
        detalle.setCantidad(detalleCarrito.getCantidad());
        carritoExistente.agregarDetalleCarrito(detalle);
        return new ResponseEntity<>(carritoRepository.save(carritoExistente), HttpStatus.OK);
    }

    @PutMapping(value = "/productoMenos/{idProducto}/carrito/{idCarrito}")
    public void eliminarProducto(@PathVariable("idProducto") Long idProducto,
    @PathVariable("idCarrito") Long idCarrito) {
        Carrito carritoExistente = carritoRepository.getById(idCarrito);
        Producto productoEliminar = productoRepository.getById(idProducto);
        List<DetalleCarrito> lista = carritoExistente.getDetalleCarrito();
        for(DetalleCarrito l:lista) {
            if(l.getProducto().getId().equals(productoEliminar.getId())) {
                carritoExistente.eliminarDetalleCarrito(l);
                detalleCarritoRepository.delete(l);
            }
        }    
    }

    @DeleteMapping(value = "/carrito/{idCarrito}")
    public void borrarPorId(
    @PathVariable("idCarrito") Long idCarrito) {
        carritoRepository.deleteById(idCarrito);
    }
}
