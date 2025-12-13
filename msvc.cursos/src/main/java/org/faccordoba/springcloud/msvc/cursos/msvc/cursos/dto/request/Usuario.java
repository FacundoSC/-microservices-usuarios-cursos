package org.faccordoba.springcloud.msvc.cursos.msvc.cursos.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;

public record Usuario (@Null Long id , @NotBlank String nombre,@NotBlank @Email String email) {
}
