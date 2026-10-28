package aeropuerto.servicio;

import aeropuerto.modelo.Aeronave;
import aeropuerto.modelo.Solicitud;
import aeropuerto.modelo.enums.Prioridad;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Registro de aeronaves y solicitudes activas (Módulo 1: Gestión de aeronaves y solicitudes).
 *
 * <p>Usa tablas hash ({@link HashMap}) indexadas por identificador de vuelo para
 * buscar en O(1) promedio y para hacer cumplir la regla "un vuelo no puede tener
 * dos solicitudes activas a la vez".</p>
 *
 * <p>Responsable: Nicole Chancusig.</p>
 */
public class RegistroSolicitudes {

    private static final String PENDIENTE = "Pendiente – Progreso 2 (Responsable: Nicole Chancusig)";

    private final Map<String, Aeronave> aeronaves;
    private final Map<String, Solicitud> activas;

    public RegistroSolicitudes() {
        this.aeronaves = new HashMap<>();
        this.activas = new HashMap<>();
    }

    /**
     * Registra una solicitud nueva.
     *
     * @return {@code false} si el vuelo ya tiene una solicitud activa
     */
    public boolean registrar(Solicitud s) {
        // TODO Progreso 2: si activas.containsKey(idVuelo) devolver false; si no, guardar
        //      aeronave y solicitud. O(1) promedio.
        throw new UnsupportedOperationException(PENDIENTE);
    }

    /** Cancela y devuelve la solicitud activa de un vuelo (o {@code null}). */
    public Solicitud cancelar(String idVuelo) {
        // TODO Progreso 2: activas.remove(idVuelo); el Aeropuerto además la elimina de la cola. O(1).
        throw new UnsupportedOperationException(PENDIENTE);
    }

    /** Busca la solicitud activa de un vuelo. */
    public Solicitud buscarPorVuelo(String idVuelo) {
        // TODO Progreso 2: activas.get(idVuelo). O(1) promedio.
        throw new UnsupportedOperationException(PENDIENTE);
    }

    /**
     * Filtra solicitudes activas por cualquier criterio (aerolínea, tipo de operación,
     * prioridad...).
     */
    public List<Solicitud> filtrar(Predicate<Solicitud> criterio) {
        // TODO Progreso 2: recorrido lineal de activas.values() aplicando el criterio. O(n).
        throw new UnsupportedOperationException(PENDIENTE);
    }

    /**
     * Clasifica automáticamente una solicitud: emergencia declarada -&gt; EMERGENCIA;
     * aterrizaje con autonomía bajo el umbral -&gt; COMBUSTIBLE_BAJO; resto -&gt; REGULAR.
     */
    public Prioridad clasificar(Solicitud s, int umbral) {
        // TODO Progreso 2: aplicar las reglas del Javadoc. O(1).
        throw new UnsupportedOperationException(PENDIENTE);
    }

    public int cantidadActivas() {
        return activas.size();
    }
}
