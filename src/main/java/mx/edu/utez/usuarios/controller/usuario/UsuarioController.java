package mx.edu.utez.usuarios.controller.usuario;

import mx.edu.utez.usuarios.model.usuario.Usuario;
import mx.edu.utez.usuarios.service.usuario.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PutMapping("/{id}/desactivar")
    public Usuario desactivarUsuario(@PathVariable Long id) {
        return usuarioService.desactivarUsuario(id);
    }
}