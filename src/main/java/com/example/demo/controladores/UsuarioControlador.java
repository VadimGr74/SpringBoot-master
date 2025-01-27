package com.example.demo.controladores;

import com.example.demo.modelos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.repositorios.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

    private final UsuarioRepository repositorioUsuarios;

    @Autowired
    public UsuarioControlador(UsuarioRepository repositorioUsuarios) {
        this.repositorioUsuarios = repositorioUsuarios;
    }

    // GET all usuarios - SELECT *
    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios() {
        List<Usuario> lista = repositorioUsuarios.findAll();
        return ResponseEntity.ok(lista);
    }

    // GET usuario by ID - SELECT * WHERE ID = ?
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable int id) {
        Optional<Usuario> usuario = repositorioUsuarios.findById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST new usuario - INSERT
    @PostMapping
    public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioPersistido = repositorioUsuarios.save(usuario);
        return ResponseEntity.status(201).body(usuarioPersistido);
    }

    // PUT update usuario - UPDATE WHERE ID = ?
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario, @PathVariable int id) {
        if (!repositorioUsuarios.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        usuario.setId(id);  // Asegurarse de que el ID no se modifique
        Usuario usuarioActualizado = repositorioUsuarios.save(usuario);
        return ResponseEntity.ok(usuarioActualizado);
    }

    // DELETE usuario - DELETE WHERE ID = ?
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable int id) {
        if (!repositorioUsuarios.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repositorioUsuarios.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}