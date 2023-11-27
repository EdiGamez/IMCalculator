package com.ievidencia.imcalculator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    private String contrasena;
    private Integer edad;
    private Double estatura;
    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<IMC> imc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Double getEstatura() {
        return estatura;
    }

    public void setEstatura(Double estatura) {
        this.estatura = estatura;
    }

    public List<IMC> getImc() {
        return imc;
    }

    public void setImc(List<IMC> imc) {
        this.imc = imc;
    }
}