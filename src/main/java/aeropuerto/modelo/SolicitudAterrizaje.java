package aeropuerto.modelo;

import aeropuerto.modelo.enums.Prioridad;
import aeropuerto.modelo.enums.TipoOperacion;

/**
 * Solicitud de aterrizaje (Módulo 1).
 *
 * <p>Solo los aterrizajes pueden clasificarse como COMBUSTIBLE_BAJO por
 * autonomía (una aeronave en tierra no corre ese riesgo).</p>
 *
 * <p>Regla de separación (valores simulados): 3 min si la operación anterior en la
 * pista fue un aterrizaje; 2 min si fue un despegue o si no hubo operación previa.</p>
 *
 * <p>Responsable: Nicole Chancusig.</p>
 */
public class SolicitudAterrizaje extends Solicitud {

    /** Separación aterrizaje tras aterrizaje (min). Ver config/parametros.properties. */
    public static final int SEPARACION_TRAS_ATERRIZAJE = 3;
    /** Separación en cualquier otra combinación (min). */
    public static final int SEPARACION_OTROS = 2;

    public SolicitudAterrizaje(Aeronave aeronave, Prioridad prioridad, int horaSolicitud) {
        super(aeronave, prioridad, horaSolicitud);
    }

    @Override
    public TipoOperacion getTipo() {
        return TipoOperacion.ATERRIZAJE;
    }

    @Override
    public int separacionRequerida(TipoOperacion anterior) {
        return anterior == TipoOperacion.ATERRIZAJE ? SEPARACION_TRAS_ATERRIZAJE : SEPARACION_OTROS;
    }
}
