package com.example.demo.controladores;
import com.example.demo.modelos.Libro;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.repositorios.LibroRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libros")  // Cambié la ruta para mayor claridad y RESTful
public class LibroControlador{

    private final LibroRepository repositorioLibros;

    @Autowired
    public LibroControlador(LibroRepository repositorioLibros){
        this.repositorioLibros = repositorioLibros;
    }

    // GET all books - SELECT *
//    @GetMapping
//    public ResponseEntity<List<Libro>> getLibros() {
//        List<Libro> lista = repositorioLibros.findAll();
//        return ResponseEntity.ok(lista);
//    }

    // GET book by ISBN - SELECT * WHERE ISBN = ?
    @GetMapping("/{isbn}")
    public ResponseEntity<Libro> getLibro(@PathVariable String isbn) {
        Optional<Libro> libro = repositorioLibros.findById(isbn);
        return libro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST new book - INSERT
    @PostMapping
    public ResponseEntity<Libro> addLibro(@Valid @RequestBody Libro libro) {
        Libro libroPersistido = repositorioLibros.save(libro);
        return ResponseEntity.status(201).body(libroPersistido);  // Código 201 Created
    }

    // PUT update book - UPDATE WHERE ISBN = ?
    @PutMapping("/{isbn}")
    public ResponseEntity<Libro> updateLibro(@RequestBody Libro libro, @PathVariable String isbn) {
        if (!repositorioLibros.existsById(isbn)) {
            return ResponseEntity.notFound().build();  // Si no existe el libro
        }
        libro.setIsbn(isbn);  // Asegurarse de que el ISBN no se modifique
        Libro libroActualizado = repositorioLibros.save(libro);
        return ResponseEntity.ok(libroActualizado);  // Código 200 OK
    }

    // DELETE book - DELETE WHERE ISBN = ?
    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteLibro(@PathVariable String isbn) {
        if (!repositorioLibros.existsById(isbn)) {
            return ResponseEntity.notFound().build();  // Si no existe el libro
        }
        repositorioLibros.deleteById(isbn);
        return ResponseEntity.noContent().build();  // Código 204 No Content
    }
}