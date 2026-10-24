package mx.edu.utez.usuarios.repository.usuarioRepository;

import mx.edu.utez.usuarios.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
