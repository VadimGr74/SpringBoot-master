package com.example.demo.controladores;

import com.example.demo.modelos.Ejemplar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.repositorios.EjemplarRepository;

import java.util.List;

@RestController
@RequestMapping("/api/ejemplares")
public class EjemplarControlador {

    @Autowired
    private EjemplarRepository ejemplarRepository;

    @GetMapping
    public List<Ejemplar> getAllEjemplares() {
        return ejemplarRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ejemplar> getEjemplarById(@PathVariable Integer id) {
        return ejemplarRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Ejemplar createEjemplar(@RequestBody Ejemplar ejemplar) {
        return ejemplarRepository.save(ejemplar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ejemplar> updateEjemplar(@PathVariable Integer id, @RequestBody Ejemplar ejemplar) {
        return ejemplarRepository.findById(id)
                .map(existing -> {
                    ejemplar.setId(existing.getId());
                    return ResponseEntity.ok(ejemplarRepository.save(ejemplar));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEjemplar(@PathVariable Integer id) {
        if (ejemplarRepository.existsById(id)) {
            ejemplarRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
