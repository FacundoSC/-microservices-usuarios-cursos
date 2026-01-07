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

    @GetMapping("/")
    List<Usuario> findAll();

    @GetMapping(value = "/{id}")
    Usuario findById(@PathVariable(value = "id") Long id, @RequestHeader(value="Authorization",required = true) String token);

    @GetMapping("/ids")
    List<Usuario> findAllById(@RequestParam(value = "ids") ArrayList<Long> ids, @RequestHeader(value="Authorization",required = true) String token);

    @GetMapping("/email/{email}")
    Usuario findByEmail(@PathVariable(value = "email") String email);

    @PostMapping(value="/")
    UsuarioResponse save(@Valid @RequestBody CrearUsuarioRequest usuario);

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable(value = "id") Long id);


}
