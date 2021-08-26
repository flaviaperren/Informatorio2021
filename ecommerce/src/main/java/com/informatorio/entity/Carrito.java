package com.informatorio.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarrito;
    @CreationTimestamp
    private LocalDate fechaCreacion;
    @UpdateTimestamp
    private LocalDate fechaModificacion;
    private Boolean estado;
    @Transient
    private String nombreDeUsuario;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleCarrito> listaCarrito = new ArrayList<>();

    public Long getId() {
        return idCarrito;
    }

    public void setId(Long idCarrito) {
        this.idCarrito = idCarrito;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombreDeUsuario() {
        return usuario.getNombre();
    }

    public List<DetalleCarrito> getDetalleCarrito() {
        return listaCarrito;
    }

    public void agregarDetalleCarrito(DetalleCarrito detalleCarrito) {
        listaCarrito.add(detalleCarrito);
        detalleCarrito.setCarrito(this);
    }

    public void eliminarDetalleCarrito(DetalleCarrito detalleCarrito) {
        listaCarrito.remove(detalleCarrito);
        detalleCarrito.setCarrito(null);
    }

    
}