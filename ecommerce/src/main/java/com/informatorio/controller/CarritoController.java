package com.informatorio.controller;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.informatorio.entity.Carrito;
import com.informatorio.entity.DetalleCarrito;
import com.informatorio.entity.Estado;
import com.informatorio.entity.Linea;
import com.informatorio.entity.Orden;
import com.informatorio.entity.Producto;
import com.informatorio.entity.Usuario;
import com.informatorio.repository.CarritoRepository;
import com.informatorio.repository.DetalleCarritoRepository;
import com.informatorio.repository.LineaRepository;
import com.informatorio.repository.OrdenRepository;
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
    @Autowired
    private OrdenRepository ordenRepository;
    @Autowired
    private LineaRepository lineaRepository;
 
    @GetMapping(value = "/carrito")
    public ResponseEntity<?> buscarCarritoTodos() {
        return new ResponseEntity<>(carritoRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/carrito/{idCarrito}") 
    public ResponseEntity<?> buscarCarritoPorId(@PathVariable("idCarrito") Long idCarrito) {
        Carrito carrito = carritoRepository.findById(idCarrito).
        orElseThrow(()->new EntityNotFoundException("No existe el carrito buscado"));
        return ResponseEntity.status(HttpStatus.OK).body(carrito);
    }

    @GetMapping(value = "/carrito/{idCarrito}/detalle")
    public List<DetalleCarrito> mostrarDetalle(@PathVariable("idCarrito") Long idCarrito) {
        Carrito carritoExistente = carritoRepository.getById(idCarrito);
        return carritoExistente.getDetalleCarrito();
    }

    @PostMapping(value = "/usuario/{idUsuario}/carrito")
    public ResponseEntity<?> crearCarrito(@PathVariable("idUsuario") Long idUsuario, 
    @Valid @RequestBody Carrito carrito) {
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        if(usuario.getCarritos().isEmpty()) {
            carrito.setUsuario(usuario);
             } else {
                 for(Carrito c:usuario.getCarritos()) {
                    if(c.getEstado()) {
                        return new ResponseEntity<>("El usuario tiene un carrito activo", HttpStatus.CONFLICT);  
                } else {
                    carrito.setUsuario(usuario);
                    }
                }       
         } return new ResponseEntity<>(carritoRepository.save(carrito), HttpStatus.CREATED);
    }

    @PutMapping(value = "/productoMas/{idProducto}/carrito/{idCarrito}")
    public ResponseEntity<?> agregarProducto(@PathVariable("idProducto") Long idProducto,
    @PathVariable("idCarrito") Long idCarrito,
    @Valid @RequestBody DetalleCarrito detalleCarrito) {
        Carrito carritoExistente = carritoRepository.getById(idCarrito);
        if(carritoExistente.getEstado()) {
            Producto productoAgregar = productoRepository.getById(idProducto);
            DetalleCarrito detalle = new DetalleCarrito();
            detalle.setCarrito(carritoExistente);
            detalle.setProducto(productoAgregar);
            detalle.setCantidad(detalleCarrito.getCantidad());
            carritoExistente.agregarDetalleCarrito(detalle);
        } else {
            return new ResponseEntity<>("No se pueden agregar productos a un carrito cerrado", HttpStatus.CONFLICT);
        }
        
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

    @PostMapping(value = "/carrito/{idCarrito}/checkout")
    public ResponseEntity<?> cerrarCarrito(@PathVariable("idCarrito") Long idCarrito) {
        Carrito carritoExistente = carritoRepository.getById(idCarrito);
        if(carritoExistente.getEstado()) {
            if (carritoExistente.getDetalleCarrito().size() >=1) {
                carritoExistente.setEstado(false);
            }
            Orden nuevaOrden = new Orden();
            nuevaOrden.setCarritoId(carritoExistente.getId());
            nuevaOrden.setEstado(Estado.CONFIRMADA);
            nuevaOrden.setUsuario(carritoExistente.getUsuario());
            nuevaOrden.setCodigoComprobante(String.valueOf(carritoExistente.getUsuario().getId()).concat(String.valueOf(carritoExistente.getId())));
            ordenRepository.save(nuevaOrden); 

            List<DetalleCarrito> detalle = carritoExistente.getDetalleCarrito();
            
            for(DetalleCarrito d:detalle) {
                Linea nuevaLinea = new Linea();
                nuevaLinea.setProductoId(d.getProducto().getId());
                nuevaLinea.setCantidad(d.getCantidad());
                nuevaLinea.setPrecioUnitario(d.getProducto().getPrecioUnitario());
                nuevaLinea.setOrden(nuevaOrden);
                lineaRepository.save(nuevaLinea);
            }
        } else {
            return new ResponseEntity<>("El carrito ya se encuentra cerrado", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(carritoRepository.save(carritoExistente), HttpStatus.OK);   
    } 

    @DeleteMapping(value = "/carrito/{idCarrito}")
    public void borrarCarritoPorId(
    @PathVariable("idCarrito") Long idCarrito) {
        carritoRepository.deleteById(idCarrito);
    }
}
