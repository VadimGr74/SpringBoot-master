package com.example.demo.controladores;

import com.example.demo.modelos.Prestamo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.repositorios.PrestamoRepository;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoControlador {

    private final PrestamoRepository repositorioPrestamos;

    @Autowired
    public PrestamoControlador(PrestamoRepository repositorioPrestamos) {
        this.repositorioPrestamos = repositorioPrestamos;
    }

    // GET all prestamos - SELECT *
    @GetMapping
    public ResponseEntity<List<Prestamo>> getPrestamos() {
        List<Prestamo> lista = repositorioPrestamos.findAll();
        return ResponseEntity.ok(lista);
    }

    // GET prestamo by ID - SELECT * WHERE ID = ?
    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> getPrestamo(@PathVariable int id) {
        Optional<Prestamo> prestamo = repositorioPrestamos.findById(id);
        return prestamo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST new prestamo - INSERT
    @PostMapping
    public ResponseEntity<Prestamo> addPrestamo(@RequestBody Prestamo prestamo) {
        Prestamo prestamoPersistido = repositorioPrestamos.save(prestamo);
        return ResponseEntity.status(201).body(prestamoPersistido);
    }

    // PUT update prestamo - UPDATE WHERE ID = ?
    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> updatePrestamo(@RequestBody Prestamo prestamo, @PathVariable int id) {
        if (!repositorioPrestamos.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        prestamo.setId(id);  // Asegurarse de que el ID no se modifique
        Prestamo prestamoActualizado = repositorioPrestamos.save(prestamo);
        return ResponseEntity.ok(prestamoActualizado);
    }

    // DELETE prestamo - DELETE WHERE ID = ?
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrestamo(@PathVariable int id) {
        if (!repositorioPrestamos.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repositorioPrestamos.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}