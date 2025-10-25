package org.faccordoba.springcloud.msvc.cursos.msvc.cursos.controllers;

import org.faccordoba.springcloud.msvc.cursos.msvc.cursos.entity.Curso;
import org.faccordoba.springcloud.msvc.cursos.msvc.cursos.services.CursoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cursos")
public class CursosController {
    private final CursoService service;
    public  CursosController(CursoService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<Curso>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable("id") Long id) {
        Optional<Curso> cursoOptional = service.findById(id);
        return cursoOptional.map
                (ResponseEntity::ok)
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
        }
        else {
            return ResponseEntity.ok(update);
        }
    }

}
