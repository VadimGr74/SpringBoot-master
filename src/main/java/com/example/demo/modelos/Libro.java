package com.example.demo.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El ISBN no puede estar vacío")
    @Pattern(regexp = "^(?:ISBN(?:-13)?:? )?(?=[0-9]{13}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)97[89][- ]?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9]$", message = "Formato de ISBN inválido")
    private String isbn;

    @NotBlank(message = "El título no puede estar vacío")
    @Size(max = 255, message = "El título no puede tener más de 255 caracteres")
    private String titulo;

    @NotBlank(message = "El autor no puede estar vacío")
    @Size(max = 255, message = "El autor no puede tener más de 255 caracteres")
    private String autor;
}