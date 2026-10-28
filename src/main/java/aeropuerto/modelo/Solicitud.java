package aeropuerto.modelo;

import aeropuerto.modelo.enums.Prioridad;
import aeropuerto.modelo.enums.TipoOperacion;

/**
 * Solicitud de pista hecha por una aeronave (Módulos 1 y 3).
 *
 * <p>Clase abstracta: cada subtipo ({@link SolicitudAterrizaje},
 * {@link SolicitudDespegue}) define su propia regla de separación mínima
 * mediante {@link #separacionRequerida(TipoOperacion)} (polimorfismo).</p>
 *
 * <p><b>Comparador compuesto</b> ({@link #compareTo(Solicitud)}): define el orden
 * del heap de la {@code ColaPrioridad}. Todas las claves son fijas mientras la
 * solicitud está dentro del heap; si alguna cambia (emergencia, nuevo reporte de
 * combustible, envejecimiento) se debe llamar a {@code ColaPrioridad.reordenar}.</p>
 * <ol>
 *   <li>Prioridad: EMERGENCIA &gt; COMBUSTIBLE_BAJO &gt; REGULAR.</li>
 *   <li>Si ambas están en COMBUSTIBLE_BAJO: menor {@code horaAgotamiento()} primero
 *       (los despegues no usan este criterio y se tratan como +infinito).</li>
 *   <li>Menor {@code horaSolicitud} primero (equivale a "mayor tiempo de espera",
 *       pero no cambia con el tiempo y por eso no rompe el invariante del heap).</li>
 *   <li>Menor {@code secuencia} primero (orden de llegada; desempate total y estable).</li>
 * </ol>
 *
 * <p>Responsable: Nicole Chancusig.</p>
 */
public abstract class Solicitud implements Comparable<Solicitud> {

    /** Contador global para asignar el número de secuencia (orden de llegada). */
    private static long contadorSecuencia = 0;

    private final long secuencia;
    private final Aeronave aeronave;
    private Prioridad prioridad;
    /** Prioridad con la que se registró; se conserva para estadísticas. */
    private final Prioridad prioridadOriginal;
    /** Minuto simulado en el que se recibió la solicitud. */
    private final int horaSolicitud;

    protected Solicitud(Aeronave aeronave, Prioridad prioridad, int horaSolicitud) {
        this.secuencia = ++contadorSecuencia;
        this.aeronave = aeronave;
        this.prioridad = prioridad;
        this.prioridadOriginal = prioridad;
        this.horaSolicitud = horaSolicitud;
    }

    /** @return tipo de operación de la solicitud (lo define cada subclase). */
    public abstract TipoOperacion getTipo();

    /**
     * Separación mínima, en minutos, que debe existir entre el inicio de la
     * operación anterior en la pista y el inicio de esta operación.
     *
     * @param anterior tipo de la última operación realizada en la pista
     *                 ({@code null} si la pista no ha tenido operaciones)
     * @return minutos de separación requeridos
     */
    public abstract int separacionRequerida(TipoOperacion anterior);

    /**
     * Tiempo que lleva esperando la solicitud.
     *
     * @param horaActual minuto simulado actual
     * @return horaActual - horaSolicitud
     */
    public int calcularEspera(int horaActual) {
        return horaActual - horaSolicitud;
    }

    /**
     * Cambia el nivel de prioridad (emergencia declarada, combustible bajo
     * detectado o envejecimiento). Nunca baja la prioridad.
     * Después de llamarlo, la cola debe reubicar la solicitud con {@code reordenar}.
     *
     * @param nueva nuevo nivel de prioridad
     */
    public void subirPrioridad(Prioridad nueva) {
        if (nueva.ordinal() < prioridad.ordinal()) {
            this.prioridad = nueva;
        }
    }

    /**
     * Comparador compuesto de 4 claves (ver Javadoc de la clase).
     * Un valor negativo significa que {@code this} debe atenderse antes que {@code otra}.
     */
    @Override
    public int compareTo(Solicitud otra) {
        // 1. Nivel de prioridad (ordinal menor = más urgente)
        int c = Integer.compare(this.prioridad.ordinal(), otra.prioridad.ordinal());
        if (c != 0) {
            return c;
        }
        // 2. Dentro de COMBUSTIBLE_BAJO: se atiende primero a quien se le acaba antes
        if (this.prioridad == Prioridad.COMBUSTIBLE_BAJO) {
            c = Integer.compare(this.claveCombustible(), otra.claveCombustible());
            if (c != 0) {
                return c;
            }
        }
        // 3. Mayor tiempo de espera = menor hora de solicitud (clave fija)
        c = Integer.compare(this.horaSolicitud, otra.horaSolicitud);
        if (c != 0) {
            return c;
        }
        // 4. Orden de llegada (garantiza un orden total y estable)
        return Long.compare(this.secuencia, otra.secuencia);
    }

    /** Clave de combustible: los despegues no compiten por combustible (+infinito). */
    private int claveCombustible() {
        return getTipo() == TipoOperacion.ATERRIZAJE
                ? aeronave.horaAgotamiento()
                : Integer.MAX_VALUE;
    }

    public long getSecuencia() { return secuencia; }
    public Aeronave getAeronave() { return aeronave; }
    public Prioridad getPrioridad() { return prioridad; }
    public Prioridad getPrioridadOriginal() { return prioridadOriginal; }
    public int getHoraSolicitud() { return horaSolicitud; }

    @Override
    public String toString() {
        return getTipo() + " " + aeronave.getIdVuelo() + " [" + prioridad + ", t=" + horaSolicitud + "]";
    }
}
