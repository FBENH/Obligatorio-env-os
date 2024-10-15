package bios.obligatorio.envios.obligatorio_envios.excepciones;

public class ExcepcionTieneVinculos extends ExcepcionProyectoEnvios{

    public ExcepcionTieneVinculos() {

    }

    public ExcepcionTieneVinculos(String mensaje) {
        super(mensaje);
    }

    public ExcepcionTieneVinculos(String mensaje, Exception excepcionInterna) {
        super(mensaje, excepcionInterna);
    }
    
}
