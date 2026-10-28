package aeropuerto.modelo;

/**
 * Resultado de asignar una solicitud a una pista (Módulo 3).
 * Se guarda en el historial (pila) y alimenta las estadísticas (Módulo 5).
 *
 * <p>Responsable: Felipe Hurtado.</p>
 */
public class Asignacion {

    private final Solicitud solicitud;
    private final Pista pista;
    /** Minuto simulado en el que inicia la operación en la pista. */
    private final int horaInicio;
    /** Minuto simulado en el que la pista queda liberada. */
    private final int horaFin;

    public Asignacion(Solicitud solicitud, Pista pista, int horaInicio, int horaFin) {
        this.solicitud = solicitud;
        this.pista = pista;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    /** @return minutos que la operación ocupa la pista (horaFin - horaInicio). */
    public int duracion() {
        // TODO Progreso 2: return horaFin - horaInicio. O(1).
        throw new UnsupportedOperationException("Pendiente – Progreso 2 (Responsable: Felipe Hurtado)");
    }

    /** @return minutos que esperó la solicitud hasta iniciar (horaInicio - horaSolicitud). */
    public int tiempoEsperado() {
        // TODO Progreso 2: return horaInicio - solicitud.getHoraSolicitud(). O(1).
        throw new UnsupportedOperationException("Pendiente – Progreso 2 (Responsable: Felipe Hurtado)");
    }

    public Solicitud getSolicitud() { return solicitud; }
    public Pista getPista() { return pista; }
    public int getHoraInicio() { return horaInicio; }
    public int getHoraFin() { return horaFin; }
}
