package aeropuerto.servicio;

import aeropuerto.estructuras.ColaPrioridad;
import aeropuerto.modelo.Asignacion;
import aeropuerto.modelo.Pista;
import aeropuerto.modelo.Solicitud;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador del simulador (integra los Módulos 1 a 5).
 *
 * <p>Es dueño (composición) de las pistas, la cola de prioridad, el registro de
 * solicitudes, el control de combustible, el historial y las estadísticas.
 * El tiempo se simula en minutos discretos ({@link #avanzarTiempo(int)}).</p>
 *
 * <p>Responsable: Felipe Hurtado (con apoyo de todo el equipo en la integración).</p>
 */
public class Aeropuerto {

    private static final String PENDIENTE = "Pendiente – Progreso 2 (Responsable: Felipe Hurtado)";

    private int horaActual;
    private final List<Pista> pistas;
    private final ColaPrioridad cola;
    private final RegistroSolicitudes registro;
    private final ControlCombustible control;
    private final HistorialOperaciones historial;
    private final Estadisticas estadisticas;
    /** Minutos de espera tras los cuales una solicitud REGULAR se promueve. */
    private final int limiteEsperaRegular;

    /**
     * @param numeroPistas         número de pistas (p), por defecto 2
     * @param umbralCombustible    minutos de autonomía para considerar combustible bajo (20)
     * @param limiteEsperaRegular  minutos de espera para promover una solicitud regular (15)
     */
    public Aeropuerto(int numeroPistas, int umbralCombustible, int limiteEsperaRegular) {
        this.horaActual = 0;
        this.pistas = new ArrayList<>();
        for (int i = 1; i <= numeroPistas; i++) {
            pistas.add(new Pista(i));
        }
        this.cola = new ColaPrioridad();
        this.registro = new RegistroSolicitudes();
        this.control = new ControlCombustible(umbralCombustible);
        this.historial = new HistorialOperaciones();
        this.estadisticas = new Estadisticas();
        this.limiteEsperaRegular = limiteEsperaRegular;
    }

    /**
     * Recibe una solicitud nueva: la registra (Módulo 1), la clasifica y la encola (Módulo 3).
     */
    public void recibirSolicitud(Solicitud s) {
        // TODO Progreso 2: registro.registrar(s) (rechaza si el vuelo ya tiene una activa);
        //      clasificar prioridad; cola.encolar(s). O(log n).
        throw new UnsupportedOperationException(PENDIENTE);
    }

    /**
     * Asignación voraz: toma la solicitud de mayor prioridad y le asigna la primera
     * pista disponible y compatible. Si no hay pista, la solicitud sigue en la cola.
     *
     * @return la asignación realizada o {@code null} si no fue posible asignar
     */
    public Asignacion asignarSiguiente() {
        // TODO Progreso 2: s = cola.verSiguiente(); recorrer pistas (O(p)) buscando
        //      estaDisponible(horaActual, s); si hay: cola.extraer() (O(log n)), crear
        //      Asignacion, pista.ocupar(a), historial.registrar(a), estadisticas.registrar(a).
        throw new UnsupportedOperationException(PENDIENTE);
    }

    /**
     * Avanza el reloj de la simulación: libera pistas, detecta combustible bajo,
     * aplica envejecimiento y asigna mientras haya pistas disponibles.
     */
    public void avanzarTiempo(int minutos) {
        // TODO Progreso 2: por cada minuto: horaActual++; liberar pistas con horaLiberacion <= horaActual;
        //      control.detectarBajoUmbral(...); aplicarEnvejecimiento(); asignarSiguiente() en bucle.
        throw new UnsupportedOperationException(PENDIENTE);
    }

    /**
     * Regla anti-inanición: las solicitudes REGULAR con espera mayor o igual a
     * {@code limiteEsperaRegular} suben a COMBUSTIBLE_BAJO (nunca a EMERGENCIA)
     * y se reubican en el heap.
     */
    public void aplicarEnvejecimiento() {
        // TODO Progreso 2: recorrer pendientes O(n); para cada regular con
        //      calcularEspera(horaActual) >= limiteEsperaRegular: subirPrioridad(COMBUSTIBLE_BAJO),
        //      cola.reordenar(s) y generar alerta ESPERA_EXCEDIDA.
        throw new UnsupportedOperationException(PENDIENTE);
    }

    public int getHoraActual() { return horaActual; }
    public List<Pista> getPistas() { return List.copyOf(pistas); }
    public ColaPrioridad getCola() { return cola; }
    public RegistroSolicitudes getRegistro() { return registro; }
    public ControlCombustible getControl() { return control; }
    public HistorialOperaciones getHistorial() { return historial; }
    public Estadisticas getEstadisticas() { return estadisticas; }
    public int getLimiteEsperaRegular() { return limiteEsperaRegular; }
}
