package com.example.demo.controladores;

import com.example.demo.modelos.Ejemplar;
import com.example.demo.modelos.Libro;
import com.example.demo.modelos.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/validaciones")
public class ValidationControlador {

    @PostMapping("/libro")
    public ResponseEntity<String> validarLibro(@Validated @RequestBody Libro libro) {
        return ResponseEntity.ok("Libro válido");
    }

    @PostMapping("/ejemplar")
    public ResponseEntity<String> validarEjemplar(@Validated @RequestBody Ejemplar ejemplar) {
        return ResponseEntity.ok("Ejemplar válido");
    }

    @PostMapping("/usuario")
    public ResponseEntity<String> validarUsuario(@Validated @RequestBody Usuario usuario) {
        return ResponseEntity.ok("Usuario válido");
    }
}