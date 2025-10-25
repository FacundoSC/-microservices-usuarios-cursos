package org.faccordoba.springcloud.msvc.usuarios.mscv.usuarios.application.mapper;

import org.faccordoba.springcloud.msvc.usuarios.mscv.usuarios.application.dto.request.CrearUsuarioRequest;
import org.faccordoba.springcloud.msvc.usuarios.mscv.usuarios.application.dto.response.UsuarioResponse;
import org.faccordoba.springcloud.msvc.usuarios.mscv.usuarios.domain.model.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface UsuarioDomainMapper {
   Usuario toDomain(CrearUsuarioRequest  usuario);
   UsuarioResponse toResponse(Usuario usuario);
   List<UsuarioResponse> toResponseList(List<Usuario> usuarios);
}
