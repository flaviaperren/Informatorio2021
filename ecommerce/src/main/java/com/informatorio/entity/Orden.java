package com.informatorio.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrden;
    @CreationTimestamp
    private LocalDate fechaCreacion;
    @Enumerated (EnumType.STRING)
    private Tipo tipo;
    @NotBlank(message = "Es necesario establecer codigo de comprobante")
    @Column(unique = true)
    private String codigoComprobante;
    @Enumerated (EnumType.STRING)
    private Estado estado;
    private Long carritoId;
    @Size(max = 200)
    private String observacion;
    @Transient 
    private Double total;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Linea> lista = new ArrayList<>();


    public Long getId() {
        return idOrden;
    }

    public void setId(Long idOrden) {
        this.idOrden = idOrden;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getCodigoComprobante() {
        return codigoComprobante;
    }

    public void setCodigoComprobante(String codigoComprobante) {
        this.codigoComprobante = codigoComprobante;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Long getCarritoId() {
        return carritoId;
    }

    public void setCarritoId(Long carritoId) {
        this.carritoId = carritoId;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Double getTotal() {
        Double totalCompra = 0.0;
        for(Linea l : this.getLinea()) {
            totalCompra = l.getSubtotal() + totalCompra;
        }
        return totalCompra;
    }

    public List<Linea> getLinea() {
        return lista;
    }

    public void agregarLinea(Linea linea) {
        lista.add(linea);
        linea.setOrden(this);
    }

    public void eliminarLinea(Linea linea) {
        lista.remove(linea);
        linea.setOrden(null);
    }


}
