package org.faccordoba.springcloud.msvc.usuarios.mscv.usuarios.domain.port.in.services;


import org.faccordoba.springcloud.msvc.usuarios.mscv.usuarios.application.dto.request.CrearUsuarioRequest;
import org.faccordoba.springcloud.msvc.usuarios.mscv.usuarios.application.dto.response.UsuarioResponse;
import org.faccordoba.springcloud.msvc.usuarios.mscv.usuarios.domain.exception.UsuarioYaExisteException;

/**
 * Puerto de entrada para el caso de uso: Crear Usuario
 * Define QUÉ se puede hacer (contrato)
 */
public interface CrearUsuarioUseCase {

    /**
     * Crea un nuevo usuario en el sistema
     *
     * @param request datos del usuario a crear
     * @return información del usuario creado
     * @throws UsuarioYaExisteException si el email ya está registrado
     * @throws ValidationException si los datos no son válidos
     */
    UsuarioResponse save(CrearUsuarioRequest request);
}