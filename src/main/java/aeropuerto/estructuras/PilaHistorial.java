package aeropuerto.estructuras;

import java.util.List;

/**
 * Pila genérica implementada con nodos enlazados (Módulo 5).
 * Se usa para el historial de operaciones: la última operación completada
 * queda en el tope, por lo que "consultar las últimas k" es natural (LIFO).
 *
 * <p>Complejidad: apilar, desapilar y verTope O(1); ultimos(k) O(k). Espacio O(m),
 * con m = operaciones completadas.</p>
 *
 * <p>Responsable: David Galarza.</p>
 *
 * @param <T> tipo de dato almacenado (en el simulador, {@code Asignacion})
 */
public class PilaHistorial<T> {

    private static final String PENDIENTE = "Pendiente – Progreso 2 (Responsable: David Galarza)";

    /** Nodo de la lista enlazada simple. */
    private static class Nodo<T> {
        private final T dato;
        private final Nodo<T> siguiente;

        private Nodo(T dato, Nodo<T> siguiente) {
            this.dato = dato;
            this.siguiente = siguiente;
        }
    }

    private Nodo<T> tope;
    private int tamano;

    public PilaHistorial() {
        this.tope = null;
        this.tamano = 0;
    }

    /** Coloca un elemento en el tope. */
    public void apilar(T dato) {
        // TODO Progreso 2: tope = new Nodo<>(dato, tope); tamano++. O(1).
        throw new UnsupportedOperationException(PENDIENTE);
    }

    /** Quita y devuelve el elemento del tope. */
    public T desapilar() {
        // TODO Progreso 2: si está vacía lanzar excepción; guardar tope.dato; tope = tope.siguiente. O(1).
        throw new UnsupportedOperationException(PENDIENTE);
    }

    /** Devuelve, sin quitarlo, el elemento del tope. */
    public T verTope() {
        // TODO Progreso 2: return tope == null ? null : tope.dato. O(1).
        throw new UnsupportedOperationException(PENDIENTE);
    }

    /** Devuelve los k elementos más recientes, del más nuevo al más antiguo. */
    public List<T> ultimos(int k) {
        // TODO Progreso 2: recorrer desde el tope k nodos sin modificar la pila. O(k).
        throw new UnsupportedOperationException(PENDIENTE);
    }

    public boolean estaVacia() {
        return tope == null;
    }

    public int tamano() {
        return tamano;
    }
}
