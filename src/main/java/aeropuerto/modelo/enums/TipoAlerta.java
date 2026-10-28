package aeropuerto.modelo.enums;

/**
 * Tipo de alerta emitida por el control de combustible y emergencias (Módulo 4).
 * Toda alerta emitida queda registrada.
 */
public enum TipoAlerta {
    /** Se declaró una emergencia para la aeronave. */
    EMERGENCIA,
    /** La autonomía restante cayó por debajo del umbral configurado. */
    COMBUSTIBLE_BAJO,
    /** Una solicitud regular superó el tiempo límite de espera y fue promovida. */
    ESPERA_EXCEDIDA
}
