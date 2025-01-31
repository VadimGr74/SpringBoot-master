package com.example.demo.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El DNI no puede estar vacío")
    @Pattern(regexp = "^[0-9]{8}[A-Za-z]$", message = "Formato de DNI inválido")
    private String dni;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 255, message = "El nombre no puede tener más de 255 caracteres")
    private String nombre;

    @NotBlank(message = "El tipo no puede estar vacío")
    @Pattern(regexp = "normal|administrador", message = "El tipo debe ser 'normal' o 'administrador'")
    private String tipo;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(regexp = ".+@gmail\\.com", message = "El email debe ser de Gmail")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 4, max = 12, message = "La contraseña debe tener entre 4 y 12 caracteres")
    private String password;

    private LocalDate penalizacionHasta;
}
