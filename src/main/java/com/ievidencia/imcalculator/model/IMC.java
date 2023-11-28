package com.ievidencia.imcalculator.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "imc")
public class IMC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "masa_corporal", nullable = false)
    private Double masaCorporal;

    @Column(name = "valor_imc", nullable = false)
    private Double valorImc;

    @Column(name = "fecha", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime fecha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Double getMasaCorporal() {
        return masaCorporal;
    }

    public void setMasaCorporal(Double masaCorporal) {
        this.masaCorporal = masaCorporal;
    }

    public Double getValorImc() {
        return valorImc;
    }

    public void setValorImc(Double valorImc) {
        this.valorImc = valorImc;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}