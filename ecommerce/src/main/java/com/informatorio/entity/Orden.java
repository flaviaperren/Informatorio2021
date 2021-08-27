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
import javax.validation.constraints.NotBlank;

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
    private Integer codigoComprobante;
    @Enumerated (EnumType.STRING)
    private Estado estado;
    private Long carritoId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

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

    public Integer getCodigoComprobante() {
        return codigoComprobante;
    }

    public void setCodigoComprobante(Integer codigoComprobante) {
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
