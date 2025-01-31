package com.example.demo.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
public class Ejemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El estado no puede estar vacío")
    @Pattern(regexp = "disponible|prestado|dañado", message = "El estado debe ser 'disponible', 'prestado' o 'dañado'")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "libro_id", nullable = false)
    private Libro libro;
}