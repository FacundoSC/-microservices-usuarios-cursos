package org.faccordoba.springcloud.msvc.usuarios.mscv.usuarios.application.dto.request;

public record CrearUsuarioRequest(
        String nombre,
        String email,
        String password) {
}
