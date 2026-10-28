package aeropuerto.estructuras;

import aeropuerto.modelo.Solicitud;

/**
 * Cola de prioridad implementada como <b>heap binario sobre arreglo</b>
 * (Módulo 3, alternativa A seleccionada).
 *
 * <p>La raíz ({@code heap[0]}) es siempre la solicitud que debe atenderse primero
 * según {@link Solicitud#compareTo(Solicitud)}. Para el nodo en la posición
 * {@code i}: padre = (i - 1) / 2, hijo izquierdo = 2i + 1, hijo derecho = 2i + 2.</p>
 *
 * <table>
 *   <caption>Complejidad esperada (n = solicitudes pendientes)</caption>
 *   <tr><th>Operación</th><th>Tiempo</th></tr>
 *   <tr><td>encolar</td><td>O(log n)</td></tr>
 *   <tr><td>extraer</td><td>O(log n)</td></tr>
 *   <tr><td>verSiguiente / tamano / estaVacia</td><td>O(1)</td></tr>
 *   <tr><td>reordenar / eliminar</td><td>O(n) localizar + O(log n) reubicar
 *       (O(log n) si se agrega un índice HashMap vuelo → posición)</td></tr>
 * </table>
 * <p>Espacio: O(n).</p>
 *
 * <p>Responsable: Felipe Hurtado.</p>
 */
public class ColaPrioridad {

    private static final int CAPACIDAD_INICIAL = 16;
    private static final String PENDIENTE = "Pendiente – Progreso 2 (Responsable: Felipe Hurtado)";

    private Solicitud[] heap;
    private int tamano;

    public ColaPrioridad() {
        this.heap = new Solicitud[CAPACIDAD_INICIAL];
        this.tamano = 0;
    }

    /** Inserta una solicitud manteniendo la propiedad de heap. */
    public void encolar(Solicitud s) {
        // TODO Progreso 2: si el arreglo está lleno, duplicar capacidad (O(n) amortizado O(1));
        //      heap[tamano] = s; subir(tamano); tamano++. Complejidad O(log n).
        throw new UnsupportedOperationException(PENDIENTE);
    }

    /** Quita y devuelve la solicitud de mayor prioridad. */
    public Solicitud extraer() {
        // TODO Progreso 2: guardar heap[0]; mover heap[tamano-1] a la raíz; tamano--;
        //      bajar(0); devolver la raíz guardada. Complejidad O(log n).
        throw new UnsupportedOperationException(PENDIENTE);
    }

    /** Devuelve, sin quitarla, la solicitud de mayor prioridad. */
    public Solicitud verSiguiente() {
        // TODO Progreso 2: return tamano == 0 ? null : heap[0]. Complejidad O(1).
        throw new UnsupportedOperationException(PENDIENTE);
    }

    /**
     * Reubica una solicitud cuya clave cambió (emergencia, combustible, envejecimiento).
     */
    public void reordenar(Solicitud s) {
        // TODO Progreso 2: localizar s con búsqueda lineal (O(n)) y aplicar subir(i) y bajar(i)
        //      (O(log n)). Mejora: índice HashMap<String, Integer> idVuelo -> posición.
        throw new UnsupportedOperationException(PENDIENTE);
    }

    /** Elimina una solicitud cancelada. */
    public boolean eliminar(Solicitud s) {
        // TODO Progreso 2: localizar (O(n)), reemplazar por el último elemento, tamano--,
        //      subir(i) y bajar(i) (O(log n)).
        throw new UnsupportedOperationException(PENDIENTE);
    }

    public int tamano() {
        return tamano;
    }

    public boolean estaVacia() {
        return tamano == 0;
    }

    /** Sift-up: intercambia el nodo con su padre mientras sea más prioritario. */
    private void subir(int i) {
        // TODO Progreso 2: while (i > 0 && heap[i].compareTo(heap[(i-1)/2]) < 0) { swap; i = (i-1)/2; }
        //      Complejidad O(log n).
        throw new UnsupportedOperationException(PENDIENTE);
    }

    /** Sift-down: intercambia el nodo con su hijo más prioritario mientras haga falta. */
    private void bajar(int i) {
        // TODO Progreso 2: elegir el hijo menor según compareTo (2i+1, 2i+2) e intercambiar
        //      mientras heap[i] sea mayor que ese hijo. Complejidad O(log n).
        throw new UnsupportedOperationException(PENDIENTE);
    }
}
