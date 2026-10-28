package aeropuerto.modelo;

import aeropuerto.modelo.enums.EstadoPista;
import aeropuerto.modelo.enums.TipoOperacion;

import java.util.EnumSet;
import java.util.Set;

/**
 * Pista del aeropuerto (Módulo 2: Gestión de pistas).
 *
 * <p>Reglas: atiende una sola operación a la vez; entre el inicio de dos
 * operaciones consecutivas debe transcurrir la separación mínima que define
 * {@link Solicitud#separacionRequerida(TipoOperacion)}; una pista CERRADA no
 * recibe asignaciones; solo acepta los tipos de operación permitidos.</p>
 *
 * <p>Para aplicar la separación, la pista guarda el tipo y la hora de inicio de
 * su última operación.</p>
 *
 * <p>Responsable: David Galarza.</p>
 */
public class Pista {

    private static final String PENDIENTE = "Pendiente – Progreso 2 (Responsable: David Galarza)";

    private final int id;
    private EstadoPista estado;
    private final Set<TipoOperacion> operacionesPermitidas;
    /** Tipo de la última operación iniciada ({@code null} si no ha tenido operaciones). */
    private TipoOperacion ultimaOperacion;
    /** Minuto simulado en el que inició la última operación. */
    private int horaUltimoInicio;
    /** Minuto simulado en el que termina la ocupación actual. */
    private int horaLiberacion;

    public Pista(int id, Set<TipoOperacion> operacionesPermitidas) {
        this.id = id;
        this.estado = EstadoPista.LIBRE;
        this.operacionesPermitidas = EnumSet.copyOf(operacionesPermitidas);
        this.ultimaOperacion = null;
        this.horaUltimoInicio = Integer.MIN_VALUE / 2;
        this.horaLiberacion = 0;
    }

    /** Crea una pista mixta (acepta aterrizajes y despegues). */
    public Pista(int id) {
        this(id, EnumSet.allOf(TipoOperacion.class));
    }

    /**
     * Indica si la pista puede iniciar la operación de {@code s} en {@code hora}.
     *
     * @return {@code true} si no está cerrada, acepta el tipo de operación y
     *         {@code hora >= horaDisponible(s)}
     */
    public boolean estaDisponible(int hora, Solicitud s) {
        // TODO Progreso 2: estado != CERRADA && operacionesPermitidas.contains(s.getTipo())
        //      && hora >= horaDisponible(s). Complejidad O(1).
        throw new UnsupportedOperationException(PENDIENTE);
    }

    /**
     * Primer minuto en el que la pista podría iniciar la operación de {@code s}.
     *
     * @return max(horaLiberacion, horaUltimoInicio + s.separacionRequerida(ultimaOperacion))
     */
    public int horaDisponible(Solicitud s) {
        // TODO Progreso 2: aplicar la fórmula del Javadoc. Complejidad O(1).
        throw new UnsupportedOperationException(PENDIENTE);
    }

    /** Marca la pista como OCUPADA y actualiza última operación y horas. */
    public void ocupar(Asignacion a) {
        // TODO Progreso 2: estado = OCUPADA; ultimaOperacion = a.getSolicitud().getTipo();
        //      horaUltimoInicio = a.getHoraInicio(); horaLiberacion = a.getHoraFin(). O(1).
        throw new UnsupportedOperationException(PENDIENTE);
    }

    /** Libera la pista al terminar la operación (OCUPADA -&gt; LIBRE). */
    public void liberar() {
        // TODO Progreso 2: si no está CERRADA, estado = LIBRE. O(1).
        throw new UnsupportedOperationException(PENDIENTE);
    }

    /** Cierra la pista: deja de recibir asignaciones. */
    public void cerrar() {
        // TODO Progreso 2: estado = CERRADA. O(1).
        throw new UnsupportedOperationException(PENDIENTE);
    }

    /** Reabre una pista cerrada. */
    public void reabrir() {
        // TODO Progreso 2: si está CERRADA, estado = LIBRE. O(1).
        throw new UnsupportedOperationException(PENDIENTE);
    }

    public int getId() { return id; }
    public EstadoPista getEstado() { return estado; }
    public Set<TipoOperacion> getOperacionesPermitidas() { return EnumSet.copyOf(operacionesPermitidas); }
    public TipoOperacion getUltimaOperacion() { return ultimaOperacion; }
    public int getHoraUltimoInicio() { return horaUltimoInicio; }
    public int getHoraLiberacion() { return horaLiberacion; }

    @Override
    public String toString() {
        return "Pista " + id + " [" + estado + "]";
    }
}
