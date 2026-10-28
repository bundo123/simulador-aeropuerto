package aeropuerto.servicio;

import aeropuerto.modelo.Asignacion;
import aeropuerto.modelo.enums.Prioridad;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Estadísticas de desempeño del simulador (Módulo 5).
 * Regla: las estadísticas se calculan solo con operaciones terminadas.
 * Los acumuladores permiten obtener promedio y máximo en O(1).
 *
 * <p>Responsable: David Galarza (reportes en conjunto con Nicole Chancusig).</p>
 */
public class Estadisticas {

    private static final String PENDIENTE = "Pendiente – Progreso 2 (Responsable: David Galarza)";

    private int totalOperaciones;
    private long sumaEspera;
    private int maxEspera;
    private final Map<Prioridad, Integer> conteoPorPrioridad;

    public Estadisticas() {
        this.totalOperaciones = 0;
        this.sumaEspera = 0;
        this.maxEspera = 0;
        this.conteoPorPrioridad = new EnumMap<>(Prioridad.class);
    }

    /** Acumula los datos de una operación terminada. */
    public void registrar(Asignacion a) {
        // TODO Progreso 2: totalOperaciones++; sumaEspera += a.tiempoEsperado();
        //      maxEspera = max(...); conteoPorPrioridad según prioridadOriginal. O(1).
        throw new UnsupportedOperationException(PENDIENTE);
    }

    /** Tiempo de espera promedio (min). */
    public double promedioEspera() {
        // TODO Progreso 2: return totalOperaciones == 0 ? 0 : (double) sumaEspera / totalOperaciones. O(1).
        throw new UnsupportedOperationException(PENDIENTE);
    }

    /** Espera promedio por nivel de prioridad original. */
    public Map<Prioridad, Double> reportePorPrioridad() {
        // TODO Progreso 2: acumular suma por prioridad y dividir para el conteo. O(1) por nivel.
        throw new UnsupportedOperationException(PENDIENTE);
    }

    /** Ordena las asignaciones por tiempo de espera, de mayor a menor. */
    public List<Asignacion> ordenarPorEspera(List<Asignacion> lista) {
        // TODO Progreso 2: implementar merge sort propio (estable). O(m log m) tiempo, O(m) espacio.
        throw new UnsupportedOperationException(PENDIENTE);
    }

    public int getTotalOperaciones() { return totalOperaciones; }
    public int getMaxEspera() { return maxEspera; }
}
