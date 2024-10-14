package bios.obligatorio.envios.obligatorio_envios.excepciones;

public class ExcepcionYaExiste extends ExcepcionProyectoEnvios {
    public ExcepcionYaExiste() {

    }

    public ExcepcionYaExiste(String mensaje) {
        super(mensaje);
    }

    public ExcepcionYaExiste(String mensaje, Exception excepcionInterna) {
        super(mensaje, excepcionInterna);
    }

}
