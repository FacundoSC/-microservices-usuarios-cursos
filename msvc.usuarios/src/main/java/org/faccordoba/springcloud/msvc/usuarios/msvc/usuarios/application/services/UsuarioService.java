package org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.application.services;

import lombok.extern.slf4j.Slf4j;
import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.application.dto.request.ActualizarUsuarioRequest;
import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.application.dto.request.CrearUsuarioRequest;
import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.application.dto.response.UsuarioResponse;
import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.application.mapper.UsuarioDomainMapper;
import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.domain.exception.UsuarioNotFound;
import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.domain.exception.UsuarioYaExisteException;
import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.domain.model.Usuario;
import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.domain.port.in.services.ActualizarUsuarioUseCase;
import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.domain.port.in.services.CrearUsuarioUseCase;
import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.domain.port.in.services.EliminarUsuarioUseCase;
import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.domain.port.in.services.ObtenerUsuarioUseCase;
import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.domain.port.out.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
public class UsuarioService implements CrearUsuarioUseCase, ObtenerUsuarioUseCase, EliminarUsuarioUseCase, ActualizarUsuarioUseCase {
    private final UsuarioRepository repository;
    private final UsuarioDomainMapper mapper;

    public UsuarioService(UsuarioRepository repository, UsuarioDomainMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
      //TODO ==================== CREAR ====================
    @Override
    @Transactional(readOnly = false)
    public UsuarioResponse save(CrearUsuarioRequest request) {
        log.info("Iniciando creación de usuario con email: {}", request.email());
        validarEmailUnico(request.email());
        Usuario usuario = mapper.toDomain(request);
        Usuario usuarioGuardado = repository.save(usuario);
        log.info("Usuario creado exitosamente con ID: {}", usuarioGuardado.getId());
        return mapper.toResponse(usuarioGuardado);
    }


    //TODO  ==================== OBTENER ====================
    @Override
    @Transactional(readOnly = true)
    public UsuarioResponse findById(Long id) {
        log.debug("Buscando usuario por ID: {}", id);
        return mapper.toResponse(repository.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponse> findAll() {
        log.debug("Obteniendo todos los usuarios");
        List<Usuario> usuarios = repository.findAll();
        return mapper.toResponseList(usuarios);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponse findByEmail(String email) {
        log.debug("Buscando usuario por email: {}", email);
        Usuario usuario = repository.findByEmail(email).orElseThrow(() -> new UsuarioNotFound(email));
        return mapper.toResponse(usuario);
    }


    //TODO ==================== ACTUALIZAR ====================
    @Override
    public UsuarioResponse upload(Long id, ActualizarUsuarioRequest request) {
        log.info("Actualizando usuario ID: {}", id);
        Usuario usuarioExistente = repository.findById(id);

        if (!usuarioExistente.getEmail().equals(request.email())) {
            validarEmailUnico(request.email());
        }
        usuarioExistente.setNombre(request.nombre());
        usuarioExistente.setEmail(request.email());
        Usuario usuarioActualizado = repository.save(usuarioExistente);
        log.info("Usuario actualizado exitosamente: {}", id);
        return mapper.toResponse(usuarioActualizado);
    }


    //TODO ==================== ELIMINAR ====================

    @Override
    public void delete(Long id) {
        log.info("Eliminando usuario ID: {}", id);
        if (repository.findById(id) == null) {
            throw new UsuarioNotFound(id);
        }
        repository.deleteById(id);
        log.info("Usuario eliminado exitosamente: {}", id);
    }

    // ==================== MÉTODOS PRIVADOS ====================

    private void validarEmailUnico(String email) {
        if (repository.existsByEmail(email)) {
            throw new UsuarioYaExisteException(email);
        }
    }
}
