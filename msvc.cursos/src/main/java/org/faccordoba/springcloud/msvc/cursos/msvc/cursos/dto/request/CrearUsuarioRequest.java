package org.faccordoba.springcloud.msvc.cursos.msvc.cursos.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CrearUsuarioRequest(
        @NotBlank String nombre,
        @NotBlank @Email String email,
        @NotBlank String password) {
}
