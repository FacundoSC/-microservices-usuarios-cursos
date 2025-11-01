package org.faccordoba.springcloud.msvc.cursos.msvc.cursos.services;

import org.faccordoba.springcloud.msvc.cursos.msvc.cursos.clients.UsuarioClientRest;
import org.faccordoba.springcloud.msvc.cursos.msvc.cursos.dto.request.CrearUsuarioRequest;
import org.faccordoba.springcloud.msvc.cursos.msvc.cursos.dto.response.UsuarioResponse;
import org.faccordoba.springcloud.msvc.cursos.msvc.cursos.exceptions.CursoNotFound;
import org.faccordoba.springcloud.msvc.cursos.msvc.cursos.dto.request.Usuario;
import org.faccordoba.springcloud.msvc.cursos.msvc.cursos.exceptions.CursoUsuarioNotFound;
import org.faccordoba.springcloud.msvc.cursos.msvc.cursos.models.entity.Curso;
import org.faccordoba.springcloud.msvc.cursos.msvc.cursos.models.entity.CursoUsuario;
import org.faccordoba.springcloud.msvc.cursos.msvc.cursos.repositories.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {
    private final CursoRepository repository;
    private final UsuarioClientRest usuarioClient;
    public CursoServiceImpl(CursoRepository repository, UsuarioClientRest usuarioClient) {
        this.repository = repository;
        this.usuarioClient = usuarioClient;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Curso> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> findById(Long id) {
        Curso curso = getCursoIfExists(id);
        if (!curso.getCursoUsuarios().isEmpty()){
            ArrayList<Long> userIds = curso.getCursoUsuarios().stream()
                    .map(CursoUsuario::getUsuarioId)
                    .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
            List<Usuario> usuarios = usuarioClient.findAllById(userIds);
            curso.setUsuarios(usuarios);
        }
        return Optional.of(curso);
    }

    @Override
    @Transactional
    public Curso save(Curso curso) {
        return repository.save(curso);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteCursoUsuarioById(Long usuarioId) {
        repository.deleteCursoUsuarioByUserId(usuarioId);
    }

    @Override
    @Transactional
    public Curso update(Long id, Curso curso) {
        Optional<Curso> cursoOptional = repository.findById(id);
        if (cursoOptional.isPresent()) {
            Curso cursoDB = cursoOptional.get();
            cursoDB.setNombre(curso.getNombre());
            return repository.save(cursoDB);
        }
        return null;
    }

    @Override
    @Transactional
    public Curso asignarUsuario(Long cursoId, Usuario usuario) {
        Curso curso = getCursoIfExists(cursoId);
        Usuario usuarioMsvc = usuarioClient.findById(usuario.id());
        CursoUsuario cursoUsuario = new CursoUsuario();
        cursoUsuario.setUsuarioId(usuarioMsvc.id());
        curso.addCursoUsuario(cursoUsuario);
        Curso cursoSave = repository.save(curso);
        return cursoSave;
    }



    @Override
    @Transactional
    public Curso desasignarUsuario(Long cursoId, Usuario usuario) {
        Curso curso = getCursoIfExists(cursoId);
        Usuario usuarioMsvc = usuarioClient.findById(usuario.id());
        CursoUsuario cursoUsuario = curso.getCursoUsuarios()
                .stream().filter(u -> u.getUsuarioId().equals(usuarioMsvc.id()))
                .findFirst().orElseThrow(() ->new CursoUsuarioNotFound(cursoId, usuarioMsvc.id()));
        curso.removeCursoUsuario(cursoUsuario);
        repository.save(curso);
        return curso;
    }

    @Override
    @Transactional
    public Curso crearUsuario(Long cursoId, CrearUsuarioRequest usuarioRequest) {
        Curso curso = getCursoIfExists(cursoId);
        UsuarioResponse usuarioResponse = usuarioClient.save(usuarioRequest);
        CursoUsuario cursoUsuario = new CursoUsuario();
        cursoUsuario.setUsuarioId(usuarioResponse.id());
        curso.addCursoUsuario(cursoUsuario);
        return repository.save(curso);
    }



    private Curso getCursoIfExists(Long cursoId) {
        Optional<Curso> cursoOptional = repository.findById(cursoId);
        if (cursoOptional.isEmpty()) {
            throw new CursoNotFound(cursoId);
        }
        return cursoOptional.get();
    }

}
