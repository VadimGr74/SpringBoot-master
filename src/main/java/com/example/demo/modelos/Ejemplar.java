package com.example.demo.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;



@Entity
public class Ejemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "El estado no puede estar vacío")
    @Pattern(regexp = "disponible|prestado|dañado", message = "El estado debe ser 'disponible', 'prestado' o 'dañado'")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "libro_id", nullable = false)
    private Libro libro;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotBlank(message = "El estado no puede estar vacío") @Pattern(regexp = "disponible|prestado|dañado", message = "El estado debe ser 'disponible', 'prestado' o 'dañado'") String getEstado() {
        return estado;
    }

    public void setEstado(@NotBlank(message = "El estado no puede estar vacío") @Pattern(regexp = "disponible|prestado|dañado", message = "El estado debe ser 'disponible', 'prestado' o 'dañado'") String estado) {
        this.estado = estado;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}