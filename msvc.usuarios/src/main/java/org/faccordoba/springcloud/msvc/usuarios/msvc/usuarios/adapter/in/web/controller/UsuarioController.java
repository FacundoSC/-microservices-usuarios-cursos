package org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.adapter.in.web.controller;

import jakarta.validation.Valid;
import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.application.dto.request.ActualizarUsuarioRequest;
import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.application.dto.request.CrearUsuarioRequest;
import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.application.dto.response.UsuarioResponse;
import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.application.services.UsuarioService;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
    private final UsuarioService service;
    private final Environment env;

    public UsuarioController(UsuarioService service, Environment env) {
        this.service = service;
        this.env = env;
    }

    @PostMapping("/")
    public ResponseEntity<UsuarioResponse> save(@Valid @RequestBody CrearUsuarioRequest usuario) {
        return ResponseEntity.status(HttpStatus.CREATED.value())
                        .body(service.save(usuario));
    }

    @GetMapping("/")
    public ResponseEntity<?> findAll() {
        Map<String, Object> response = new HashMap<>();
        response.put("usuarios", service.findAll());
        response.put("podsInfo", env.getProperty("MY_POD_NAME")+" - "+env.getProperty("MY_POD_IP"));
        response.put("texto", env.getProperty("config.texto"));
        return ResponseEntity.ok(response);
    }
    @GetMapping("")
    public ResponseEntity<?> findAllById(@RequestParam(value = "ids") ArrayList<Long> ids) {
        return ResponseEntity.ok(service.findAllById(ids));
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
                                    @Valid @RequestBody ActualizarUsuarioRequest request) {
        return ResponseEntity.ok(service.upload(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
