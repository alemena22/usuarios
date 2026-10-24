package mx.edu.utez.usuarios.service.usuario;

import mx.edu.utez.usuarios.exception.CustomExceptions.CustomBadRequestException;
import mx.edu.utez.usuarios.model.usuario.Usuario;
import mx.edu.utez.usuarios.repository.usuarioRepository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario desactivarUsuario(Long id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        if (!usuarioOpt.isPresent()) {
            throw new CustomBadRequestException("El usuario no existe");
        }

        Usuario usuario = usuarioOpt.get();
        usuario.setActivo(false);
        return usuarioRepository.save(usuario);
    }
}