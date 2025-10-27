package org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.adapter.in.web.controller;

import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.application.dto.request.ActualizarUsuarioRequest;
import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.application.dto.request.CrearUsuarioRequest;
import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.application.dto.response.UsuarioResponse;
import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.application.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<UsuarioResponse> save(@RequestBody CrearUsuarioRequest usuario) {
        return ResponseEntity.status(HttpStatus.CREATED.value())
                        .body(service.save(usuario));
    }

    @GetMapping("/")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable(value = "email") String email) {
        return ResponseEntity.ok(service.findByEmail(email));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id,
                                    @RequestBody ActualizarUsuarioRequest request) {
        return ResponseEntity.ok(service.upload(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
