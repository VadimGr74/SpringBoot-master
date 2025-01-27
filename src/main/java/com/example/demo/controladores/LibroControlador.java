package com.example.demo.controladores;
import com.example.demo.modelos.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.repositorios.LibroRepository;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroControlador {

    @Autowired
    private LibroRepository libroRepository;

    @GetMapping
    public List<Libro> getAllLibros() {
        return libroRepository.findAll();
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Libro> getLibroByIsbn(@PathVariable String isbn) {
        return libroRepository.findById(isbn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Libro createLibro(@RequestBody Libro libro) {
        return libroRepository.save(libro);
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<Libro> updateLibro(@PathVariable String isbn, @RequestBody Libro libro) {
        return libroRepository.findById(isbn)
                .map(existing -> {
                    libro.setIsbn(existing.getIsbn());
                    return ResponseEntity.ok(libroRepository.save(libro));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteLibro(@PathVariable String isbn) {
        if (libroRepository.existsById(isbn)) {
            libroRepository.deleteById(isbn);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}