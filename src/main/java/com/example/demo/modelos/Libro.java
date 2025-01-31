package com.example.demo.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


import java.util.List;


@Entity
public class Libro {
    public Libro() {

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "El ISBN no puede estar vacío")
    @Pattern(regexp = "^(?:ISBN(?:-13)?:? )?(?=[0-9]{13}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)97[89][- ]?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9]$", message = "Formato de ISBN inválido")
    private String isbn;

    @NotBlank(message = "El título no puede estar vacío")
    @Size(max = 255, message = "El título no puede tener más de 255 caracteres")
    private String titulo;

    @NotBlank(message = "El autor no puede estar vacío")
    @Size(max = 255, message = "El autor no puede tener más de 255 caracteres")
    private String autor;

    public @NotBlank(message = "El ISBN no puede estar vacío") @Pattern(regexp = "^(?:ISBN(?:-13)?:? )?(?=[0-9]{13}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)97[89][- ]?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9]$", message = "Formato de ISBN inválido") String getIsbn() {
        return isbn;
    }

    public void setIsbn(@NotBlank(message = "El ISBN no puede estar vacío") @Pattern(regexp = "^(?:ISBN(?:-13)?:? )?(?=[0-9]{13}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)97[89][- ]?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9]$", message = "Formato de ISBN inválido") String isbn) {
        this.isbn = isbn;
    }

    public @NotBlank(message = "El título no puede estar vacío") @Size(max = 255, message = "El título no puede tener más de 255 caracteres") String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotBlank(message = "El título no puede estar vacío") @Size(max = 255, message = "El título no puede tener más de 255 caracteres") String titulo) {
        this.titulo = titulo;
    }

    public @NotBlank(message = "El autor no puede estar vacío") @Size(max = 255, message = "El autor no puede tener más de 255 caracteres") String getAutor() {
        return autor;
    }

    public void setAutor(@NotBlank(message = "El autor no puede estar vacío") @Size(max = 255, message = "El autor no puede tener más de 255 caracteres") String autor) {
        this.autor = autor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}