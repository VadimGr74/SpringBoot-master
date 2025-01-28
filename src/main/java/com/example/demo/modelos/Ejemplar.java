package com.example.demo.modelos;

import jakarta.persistence.*;

@Entity
public class Ejemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public EstadoEjemplar getEstado() {
        return estado;
    }

    public void setEstado(EstadoEjemplar estado) {
        this.estado = estado;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "isbn", nullable = false)
    private Libro libro;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoEjemplar estado = EstadoEjemplar.Disponible;

    // Getters y Setters

    public enum EstadoEjemplar {
        Disponible,
        Prestado,
        Dañado
    }
}