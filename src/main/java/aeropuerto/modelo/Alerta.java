package aeropuerto.modelo;

import aeropuerto.modelo.enums.TipoAlerta;

/**
 * Alerta emitida por el control de combustible y emergencias (Módulo 4).
 * Las alertas son inmutables y quedan registradas en {@code ControlCombustible}.
 *
 * <p>Responsable: Mateo Izquierdo.</p>
 */
public class Alerta {

    private final TipoAlerta tipo;
    private final Aeronave aeronave;
    private final int hora;
    private final String descripcion;

    public Alerta(TipoAlerta tipo, Aeronave aeronave, int hora, String descripcion) {
        this.tipo = tipo;
        this.aeronave = aeronave;
        this.hora = hora;
        this.descripcion = descripcion;
    }

    public TipoAlerta getTipo() { return tipo; }
    public Aeronave getAeronave() { return aeronave; }
    public int getHora() { return hora; }
    public String getDescripcion() { return descripcion; }

    @Override
    public String toString() {
        return "[t=" + hora + "] " + tipo + " " + aeronave.getIdVuelo() + ": " + descripcion;
    }
}
