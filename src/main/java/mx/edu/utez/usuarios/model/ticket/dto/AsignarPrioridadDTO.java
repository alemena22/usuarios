package mx.edu.utez.usuarios.model.ticket.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.usuarios.model.ticket.Prioridad;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AsignarPrioridadDTO{
    @NotNull(message = "La prioridad es obligatoria")
    private Prioridad prioridad;

}