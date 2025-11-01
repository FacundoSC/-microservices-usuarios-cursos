package org.faccordoba.springcloud.msvc.cursos.msvc.cursos.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name="curso_usuarios")
@Getter
@Setter
public class CursoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="usuario_id", unique = true)
    private Long  usuarioId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CursoUsuario that = (CursoUsuario) o;
        return Objects.equals(id, that.id) && Objects.equals(usuarioId, that.usuarioId);
    }
}
