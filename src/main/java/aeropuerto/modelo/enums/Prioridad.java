package aeropuerto.modelo.enums;

/**
 * Nivel de prioridad de una solicitud de pista (Módulos 1, 3 y 4).
 *
 * <p>El orden de declaración ES el orden de atención: {@code ordinal()} menor
 * significa mayor prioridad. El comparador compuesto de {@code Solicitud}
 * usa este orden como primera clave.</p>
 *
 * <ol>
 *   <li>{@link #EMERGENCIA}: precedencia absoluta sobre cualquier otra solicitud.</li>
 *   <li>{@link #COMBUSTIBLE_BAJO}: aterrizaje con autonomía menor al umbral (20 min
 *       por defecto) o solicitud regular promovida por envejecimiento.</li>
 *   <li>{@link #REGULAR}: operación normal.</li>
 * </ol>
 */
public enum Prioridad {
    EMERGENCIA,
    COMBUSTIBLE_BAJO,
    REGULAR
}
