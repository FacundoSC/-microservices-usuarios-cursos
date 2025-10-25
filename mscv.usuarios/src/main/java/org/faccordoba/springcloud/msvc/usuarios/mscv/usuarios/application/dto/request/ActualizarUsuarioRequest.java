package org.faccordoba.springcloud.msvc.usuarios.mscv.usuarios.application.dto.request;

public record ActualizarUsuarioRequest(
        String nombre,
        String email,
        String password) {
}
