package aeropuerto.ui;

import aeropuerto.servicio.Aeropuerto;

/**
 * Interfaz de consola del simulador.
 * Opciones previstas: registrar solicitud, declarar emergencia, actualizar combustible,
 * avanzar tiempo, ver cola, ver pistas, ver historial, ver estadísticas, salir.
 *
 * <p>Responsable: todo el equipo (fase de integración, Progreso 2).</p>
 */
public class MenuConsola {

    private final Aeropuerto aeropuerto;

    public MenuConsola(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }

    /** Muestra el menú y procesa las opciones hasta que el usuario sale. */
    public void iniciar() {
        // TODO Progreso 2: bucle con Scanner y switch sobre las opciones del Javadoc.
        throw new UnsupportedOperationException("Pendiente – Progreso 2 (Responsable: todo el equipo)");
    }

    public Aeropuerto getAeropuerto() {
        return aeropuerto;
    }
}
