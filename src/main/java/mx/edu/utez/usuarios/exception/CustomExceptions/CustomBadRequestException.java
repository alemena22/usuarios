package mx.edu.utez.usuarios.exception.CustomExceptions;

public class CustomBadRequestException extends RuntimeException{
    public CustomBadRequestException(String mensaje){
        super(mensaje);
    }
}
