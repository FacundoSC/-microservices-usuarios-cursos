package org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.adapter.out.external.feing;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-cursos", url= "http://localhost:8002")
public interface CursoClienteRest {
    @DeleteMapping("/api/v1/cursos/usuarios/{id}")
    void eliminarCursoUsuario(@PathVariable("id") Long id);
}
