package org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.adapter.out.persistence.mapper;

import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.adapter.out.persistence.entity.UsuarioEntity;
import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.domain.model.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
     UsuarioEntity toEntity(Usuario request);
     Usuario toDomain(UsuarioEntity entity);
     List<Usuario> toDomainList(List<UsuarioEntity> entities);

}
