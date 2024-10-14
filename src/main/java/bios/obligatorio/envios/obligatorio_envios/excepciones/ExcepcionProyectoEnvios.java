package bios.obligatorio.envios.obligatorio_envios.excepciones;

public class ExcepcionProyectoEnvios extends Exception {
    
    public ExcepcionProyectoEnvios () {

    }

    public ExcepcionProyectoEnvios (String mensaje) {
        super(mensaje);
    }

    public ExcepcionProyectoEnvios (String mensaje, Exception exInterna){
        super(mensaje,exInterna);
    }
}
