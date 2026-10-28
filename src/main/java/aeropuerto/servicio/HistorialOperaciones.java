package aeropuerto.servicio;

import aeropuerto.estructuras.PilaHistorial;
import aeropuerto.modelo.Asignacion;

import java.util.List;
import java.util.Map;

/**
 * Historial de operaciones completadas (Módulo 5).
 * Usa una {@link PilaHistorial} para consultar de forma natural las últimas operaciones.
 * Regla: toda operación completada se registra una sola vez.
 *
 * <p>Responsable: David Galarza.</p>
 */
public class HistorialOperaciones {

    private static final String PENDIENTE = "Pendiente – Progreso 2 (Responsable: David Galarza)";

    private final PilaHistorial<Asignacion> pila;

    public HistorialOperaciones() {
        this.pila = new PilaHistorial<>();
    }

    /** Apila una asignación completada. */
    public void registrar(Asignacion a) {
        // TODO Progreso 2: pila.apilar(a). O(1).
        throw new UnsupportedOperationException(PENDIENTE);
    }

    /** Devuelve las últimas k operaciones (la más reciente primero). */
    public List<Asignacion> consultarUltimas(int k) {
        // TODO Progreso 2: pila.ultimos(k). O(k).
        throw new UnsupportedOperationException(PENDIENTE);
    }

    /** Número de operaciones atendidas por cada pista (id de pista -&gt; cantidad). */
    public Map<Integer, Integer> reportePorPista() {
        // TODO Progreso 2: recorrer el historial y contar en un HashMap. O(m).
        throw new UnsupportedOperationException(PENDIENTE);
    }

    public int totalRegistradas() {
        return pila.tamano();
    }
}
