package com.informatorio.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class DetalleCarrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleCarrito;
    @Positive
    private Integer cantidad;
    @Transient 
    private Double subtotal;

    @JsonIgnoreProperties({"hibernateLazyInitializer" , "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Producto producto;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Carrito carrito;

    public Long getId() {
        return idDetalleCarrito;
    }

    public void setId(Long idDetalleCarrito) {
        this.idDetalleCarrito = idDetalleCarrito;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubtotal() {
        return (cantidad * producto.getPrecioUnitario());
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

}
