package org.faccordoba.springcloud.msvc.cursos.msvc.cursos.clients;

import jakarta.validation.Valid;
import org.faccordoba.springcloud.msvc.cursos.msvc.cursos.dto.request.CrearUsuarioRequest;
import org.faccordoba.springcloud.msvc.cursos.msvc.cursos.dto.request.Usuario;
import org.faccordoba.springcloud.msvc.cursos.msvc.cursos.dto.response.UsuarioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "msvc-usuarios", url= "${msvc.usuarios.url}")
public interface UsuarioClientRest {


    @PostMapping(value="/api/v1/usuarios/")
    UsuarioResponse save(@Valid @RequestBody CrearUsuarioRequest usuario);


    @GetMapping(value = "/api/v1/usuarios/{id}")
    Usuario findById(@PathVariable(value = "id") Long id);

    @GetMapping("/api/v1/usuarios/")
    List<Usuario> findAll();

    @GetMapping("/api/v1/usuarios/email/{email}")
    Usuario findByEmail(@PathVariable(value = "email") String email);

    @DeleteMapping("/api/v1/usuarios/{id}")
    ResponseEntity<?> delete(@PathVariable(value = "id") Long id);

    @GetMapping("/api/v1/usuarios")
    List<Usuario> findAllById(@RequestParam(value = "ids") ArrayList<Long> ids);


}
