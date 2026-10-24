package mx.edu.utez.usuarios.service.ticket;

import mx.edu.utez.usuarios.exception.CustomExceptions.CustomBadRequestException;
import mx.edu.utez.usuarios.model.ticket.Estado;
import mx.edu.utez.usuarios.model.ticket.Prioridad;
import mx.edu.utez.usuarios.model.ticket.Ticket;
import mx.edu.utez.usuarios.model.ticket.dto.CrearTicketDTO;
import mx.edu.utez.usuarios.model.usuario.Usuario;
import mx.edu.utez.usuarios.repository.ticketRepository.TicketRepository;
import mx.edu.utez.usuarios.repository.usuarioRepository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UsuarioRepository usuarioRepository;

    public TicketService(TicketRepository ticketRepository, UsuarioRepository usuarioRepository) {
        this.ticketRepository = ticketRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Ticket crearTicket(CrearTicketDTO dto) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(dto.getUsuarioId());

        if (!usuarioOpt.isPresent()) {
            throw new CustomBadRequestException("El usuario no existe");
        }

        Usuario usuario = usuarioOpt.get();

        if (!usuario.isActivo()) {
            throw new CustomBadRequestException("El usuario no está activo");
        }

        Ticket ticket = new Ticket();
        ticket.setDescripcion(dto.getDescripcion());
        ticket.setEstado(Estado.ABIERTO);
        ticket.setPrioridad(dto.getPrioridad());
        ticket.setUsuario(usuario);

        return ticketRepository.save(ticket);
    }

    public Ticket actualizarEstadoTicket(Long id) {
        Optional<Ticket> ticketOpt = ticketRepository.findById(id);

        if (!ticketOpt.isPresent()) {
            throw new CustomBadRequestException("El ticket no existe");
        }

        Ticket ticket = ticketOpt.get();

        if (ticket.getEstado() == Estado.CERRADO) {
            throw new CustomBadRequestException("El ticket ya está cerrado y no se puede modificar");
        }

        if (ticket.getEstado() == Estado.ABIERTO) {
            ticket.setEstado(Estado.EN_PROCESO);
        } else if (ticket.getEstado() == Estado.EN_PROCESO) {
            ticket.setEstado(Estado.CERRADO);
        }

        return ticketRepository.save(ticket);
    }

    public Ticket reasignarPrioridad(Long id, Prioridad nuevaPrioridad) {
        Optional<Ticket> ticketOpt = ticketRepository.findById(id);

        if (!ticketOpt.isPresent()) {
            throw new CustomBadRequestException("El ticket no existe");
        }

        Ticket ticket = ticketOpt.get();

        if (ticket.getEstado() == Estado.CERRADO) {
            throw new CustomBadRequestException("No se puede cambiar la prioridad de un ticket cerrado");
        }

        ticket.setPrioridad(nuevaPrioridad);
        return ticketRepository.save(ticket);
    }

    public List<Ticket> obtenerTicketsUsuario(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new CustomBadRequestException("El usuario no existe");
        }
        return ticketRepository.findByUsuarioId(usuarioId);
    }
}