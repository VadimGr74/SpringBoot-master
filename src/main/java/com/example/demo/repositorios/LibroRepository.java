package com.example.demo.repositorios;

import com.example.demo.modelos.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, String> {
    List<Libro> findByTituloContainingIgnoreCase(String titulo);
}