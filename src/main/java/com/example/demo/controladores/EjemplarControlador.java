package com.example.demo.controladores;

import com.example.demo.modelos.Ejemplar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.repositorios.EjemplarRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ejemplares")
public class EjemplarControlador {

    private final EjemplarRepository repositorioEjemplares;

    @Autowired
    public EjemplarControlador(EjemplarRepository repositorioEjemplares) {
        this.repositorioEjemplares = repositorioEjemplares;
    }

    // GET all ejemplares - SELECT *
    @GetMapping
    public ResponseEntity<List<Ejemplar>> getEjemplares() {
        List<Ejemplar> lista = repositorioEjemplares.findAll();
        return ResponseEntity.ok(lista);
    }

    // GET ejemplar by ID - SELECT * WHERE ID = ?
    @GetMapping("/{id}")
    public ResponseEntity<Ejemplar> getEjemplar(@PathVariable int id) {
        Optional<Ejemplar> ejemplar = repositorioEjemplares.findById(id);
        return ejemplar.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST new ejemplar - INSERT
    @PostMapping
    public ResponseEntity<Ejemplar> addEjemplar(@RequestBody Ejemplar ejemplar) {
        Ejemplar ejemplarPersistido = repositorioEjemplares.save(ejemplar);
        return ResponseEntity.status(201).body(ejemplarPersistido);
    }

    // PUT update ejemplar - UPDATE WHERE ID = ?
    @PutMapping("/{id}")
    public ResponseEntity<Ejemplar> updateEjemplar(@RequestBody Ejemplar ejemplar, @PathVariable int id) {
        if (!repositorioEjemplares.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ejemplar.setId(id);  // Asegurarse de que el ID no se modifique
        Ejemplar ejemplarActualizado = repositorioEjemplares.save(ejemplar);
        return ResponseEntity.ok(ejemplarActualizado);
    }

    // DELETE ejemplar - DELETE WHERE ID = ?
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEjemplar(@PathVariable int id) {
        if (!repositorioEjemplares.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repositorioEjemplares.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}