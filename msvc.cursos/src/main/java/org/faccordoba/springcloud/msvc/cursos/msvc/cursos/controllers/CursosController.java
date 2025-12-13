package org.faccordoba.springcloud.msvc.cursos.msvc.cursos.controllers;

import org.faccordoba.springcloud.msvc.cursos.msvc.cursos.dto.request.CrearUsuarioRequest;
import org.faccordoba.springcloud.msvc.cursos.msvc.cursos.dto.request.Usuario;
import org.faccordoba.springcloud.msvc.cursos.msvc.cursos.models.entity.Curso;
import org.faccordoba.springcloud.msvc.cursos.msvc.cursos.services.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CursosController {
    private final CursoService service;

    public CursosController(CursoService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<Curso>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable("id") Long id) {
        Optional<Curso> cursoOptional = service.findById(id);
        return cursoOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Curso> save(@RequestBody Curso curso) {
        return ResponseEntity.ok(service.save(curso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Curso> delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> update(@PathVariable("id") Long id, @RequestBody Curso curso) {
        Curso update = service.update(id, curso);
        if (update == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(update);
        }
    }

    @PutMapping("/{cursoId}/asignar-usuario/")
    public ResponseEntity<Curso> asignarUsuario(@PathVariable("cursoId") Long cursoId, @RequestBody Usuario usuario) {
        Curso curso = service.asignarUsuario(cursoId, usuario);
        return ResponseEntity.status(HttpStatus.OK).body(curso);
    }

    @PutMapping("{cursoId}/desasignar-usuario/")
    public ResponseEntity<Curso> desasignarUsuario(@PathVariable("cursoId") Long cursoId,
            @RequestBody Usuario usuario) {
        Curso curso = service.desasignarUsuario(cursoId, usuario);
        return ResponseEntity.status(HttpStatus.OK).body(curso);
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Curso> eliminarCursoUsuario(@PathVariable("id") Long id) {
        service.deleteCursoUsuarioById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{cursoId}/usuario/")
    public ResponseEntity<Curso> crearUsuario(@PathVariable("cursoId") Long cursoId,
            @RequestBody CrearUsuarioRequest usuarioRequest) {
        Curso curso = service.crearUsuario(cursoId, usuarioRequest);
        return ResponseEntity.ok(curso);
    }

}
