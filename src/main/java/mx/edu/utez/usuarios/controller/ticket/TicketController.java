package mx.edu.utez.usuarios.controller.ticket;

import jakarta.validation.Valid;
import mx.edu.utez.usuarios.model.ticket.Ticket;
import mx.edu.utez.usuarios.model.ticket.dto.AsignarPrioridadDTO;
import mx.edu.utez.usuarios.model.ticket.dto.CrearTicketDTO;
import mx.edu.utez.usuarios.service.ticket.TicketService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public Ticket crearTicket(@Valid @RequestBody CrearTicketDTO dto) {
        return ticketService.crearTicket(dto);
    }

    @PutMapping("/{id}/estado")
    public Ticket actualizarEstado(@PathVariable Long id) {
        return ticketService.actualizarEstadoTicket(id);
    }

    @PutMapping("/{id}/prioridad")
    public Ticket reasignarPrioridad(@PathVariable Long id, @Valid @RequestBody AsignarPrioridadDTO dto) {
        return ticketService.reasignarPrioridad(id, dto.getPrioridad());
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Ticket> obtenerTicketsUsuario(@PathVariable Long usuarioId) {
        return ticketService.obtenerTicketsUsuario(usuarioId);
    }
}