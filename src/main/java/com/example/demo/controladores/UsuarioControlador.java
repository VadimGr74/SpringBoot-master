package com.example.demo.controladores;

import com.example.demo.modelos.Usuario;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
//    @GetMapping
//    public ResponseEntity<List<Usuario>> getUsuarios() {
//        List<Usuario> lista = repositorioUsuarios.findAll();
//        return ResponseEntity.ok(lista);
//    }

    // GET usuario by ID - SELECT * WHERE ID = ?
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable int id) {
        Optional<Usuario> usuario = repositorioUsuarios.findById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST new usuario - INSERT
  @Transactional
  @PostMapping
  public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody Usuario usuario) {
      // Validación adicional si es necesario
      if (usuario == null) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
      }

      // Guardar el nuevo usuario
      Usuario usuarioCreado = repositorioUsuarios.save(usuario);

      // Retornar el usuario creado con un status 201 (Created)
      return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
  }

    // PUT update usuario - UPDATE WHERE ID = ?
    @Transactional //evitar que varias transacciones crean deadlock
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@RequestBody Usuario usuario, @PathVariable int id) {
        Usuario usuarioExistente = repositorioUsuarios.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setPassword(usuario.getPassword());
        usuarioExistente.setPenalizacionHasta(usuario.getPenalizacionHasta());
        usuarioExistente.setTipo(usuario.getTipo());

        Usuario usuarioActualizado = repositorioUsuarios.saveAndFlush(usuarioExistente);
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