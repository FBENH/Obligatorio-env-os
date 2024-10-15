package bios.obligatorio.envios.obligatorio_envios.excepciones;

public class ExcepcionNoExiste extends ExcepcionProyectoEnvios {
    public ExcepcionNoExiste() {

    }

    public ExcepcionNoExiste(String mensaje) {
        super(mensaje);
    }

    public ExcepcionNoExiste(String mensaje, Exception excepcionInterna) {
        super(mensaje,excepcionInterna);
    }
}
