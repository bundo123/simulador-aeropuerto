package aeropuerto.servicio;

import aeropuerto.modelo.Aeronave;
import aeropuerto.modelo.Alerta;
import aeropuerto.modelo.Solicitud;
import aeropuerto.modelo.enums.TipoAlerta;

import java.util.ArrayList;
import java.util.List;

/**
 * Control de emergencias y combustible (Módulo 4).
 *
 * <p>Reglas: una emergencia pasa por delante de toda la cola; si la autonomía de
 * un aterrizaje baja del umbral, su prioridad sube a COMBUSTIBLE_BAJO de forma
 * automática; toda alerta emitida se registra.</p>
 *
 * <p>Responsable: Mateo Izquierdo.</p>
 */
public class ControlCombustible {

    private static final String PENDIENTE = "Pendiente – Progreso 2 (Responsable: Mateo Izquierdo)";

    private final int umbralMinutos;
    private final List<Alerta> alertas;

    public ControlCombustible(int umbralMinutos) {
        this.umbralMinutos = umbralMinutos;
        this.alertas = new ArrayList<>();
    }

    /** Declara una emergencia: sube la solicitud a EMERGENCIA y genera la alerta. */
    public Alerta declararEmergencia(Solicitud s, String motivo) {
        // TODO Progreso 2: s.subirPrioridad(EMERGENCIA); generarAlerta(EMERGENCIA, ...);
        //      el Aeropuerto llama cola.reordenar(s). O(1) + reordenar.
        throw new UnsupportedOperationException(PENDIENTE);
    }

    /** Registra un nuevo reporte de combustible de la aeronave de la solicitud. */
    public void actualizarCombustible(Solicitud s, int minutos, int hora) {
        // TODO Progreso 2: s.getAeronave().actualizarCombustible(minutos, hora);
        //      si queda bajo el umbral, escalar prioridad; reordenar en la cola. O(1) + reordenar.
        throw new UnsupportedOperationException(PENDIENTE);
    }

    /**
     * Detecta los aterrizajes pendientes cuya autonomía cayó bajo el umbral y escala su prioridad.
     *
     * @return solicitudes escaladas en esta revisión
     */
    public List<Solicitud> detectarBajoUmbral(List<Solicitud> pendientes, int hora) {
        // TODO Progreso 2: recorrido lineal O(n) usando Aeronave.estaBajoUmbral(umbralMinutos, hora).
        throw new UnsupportedOperationException(PENDIENTE);
    }

    /** Lista los vuelos críticos (emergencia y combustible bajo) ordenados por urgencia. */
    public List<Solicitud> listarCriticos() {
        // TODO Progreso 2: filtrar críticos y ordenarlos con compareTo (O(k log k)).
        throw new UnsupportedOperationException(PENDIENTE);
    }

    /** Crea y registra una alerta. */
    public Alerta generarAlerta(TipoAlerta tipo, Aeronave a, int hora) {
        // TODO Progreso 2: crear Alerta con descripción y agregarla a la lista. O(1) amortizado.
        throw new UnsupportedOperationException(PENDIENTE);
    }

    public int getUmbralMinutos() { return umbralMinutos; }
    public List<Alerta> getAlertas() { return List.copyOf(alertas); }
}
