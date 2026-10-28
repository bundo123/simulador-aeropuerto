package aeropuerto.modelo;

import aeropuerto.modelo.enums.Prioridad;
import aeropuerto.modelo.enums.TipoOperacion;

/**
 * Solicitud de despegue (Módulo 1).
 *
 * <p>Regla de separación (valores simulados): 2 min tras cualquier operación.
 * Un despegue no se clasifica como COMBUSTIBLE_BAJO; puede llegar a ese nivel
 * únicamente por envejecimiento o pasar a EMERGENCIA si se declara.</p>
 *
 * <p>Responsable: Nicole Chancusig.</p>
 */
public class SolicitudDespegue extends Solicitud {

    /** Separación de un despegue tras cualquier operación (min). */
    public static final int SEPARACION_DESPEGUE = 2;

    public SolicitudDespegue(Aeronave aeronave, Prioridad prioridad, int horaSolicitud) {
        super(aeronave, prioridad, horaSolicitud);
    }

    @Override
    public TipoOperacion getTipo() {
        return TipoOperacion.DESPEGUE;
    }

    @Override
    public int separacionRequerida(TipoOperacion anterior) {
        return SEPARACION_DESPEGUE;
    }
}
