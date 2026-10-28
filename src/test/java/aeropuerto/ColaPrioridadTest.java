package aeropuerto;

import aeropuerto.estructuras.ColaPrioridad;
import aeropuerto.modelo.Aeronave;
import aeropuerto.modelo.Solicitud;
import aeropuerto.modelo.SolicitudAterrizaje;
import aeropuerto.modelo.SolicitudDespegue;
import aeropuerto.modelo.enums.Prioridad;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Pruebas del comparador compuesto (implementado en Progreso 1) y de la cola de
 * prioridad (deshabilitadas hasta implementar el heap en Progreso 2).
 */
class ColaPrioridadTest {

    private static Aeronave avion(String id, int combustible, int hora) {
        return new Aeronave(id, "Aerolinea", "A320", combustible, hora);
    }

    @Test
    void emergenciaSeAtiendeAntesQueRegular() {
        Solicitud regular = new SolicitudAterrizaje(avion("REG1", 90, 0), Prioridad.REGULAR, 0);
        Solicitud emergencia = new SolicitudAterrizaje(avion("EMG1", 90, 10), Prioridad.EMERGENCIA, 10);
        assertTrue(emergencia.compareTo(regular) < 0, "La emergencia debe ir primero aunque llegó después");
    }

    @Test
    void combustibleBajoAntesQueRegular() {
        Solicitud regular = new SolicitudDespegue(avion("REG2", 120, 0), Prioridad.REGULAR, 0);
        Solicitud bajo = new SolicitudAterrizaje(avion("CB1", 15, 5), Prioridad.COMBUSTIBLE_BAJO, 5);
        assertTrue(bajo.compareTo(regular) < 0);
    }

    @Test
    void enCombustibleBajoPrimeroElQueSeAgotaAntes() {
        // CB1 se agota en el minuto 2 + 18 = 20; CB2 en el minuto 0 + 25 = 25
        Solicitud cb2 = new SolicitudAterrizaje(avion("CB2", 25, 0), Prioridad.COMBUSTIBLE_BAJO, 0);
        Solicitud cb1 = new SolicitudAterrizaje(avion("CB1", 18, 2), Prioridad.COMBUSTIBLE_BAJO, 2);
        assertTrue(cb1.compareTo(cb2) < 0, "Debe ir primero el que se queda sin combustible antes");
    }

    @Test
    void aIgualPrioridadPrimeroLaDeMenorHoraSolicitud() {
        Solicitud tarde = new SolicitudDespegue(avion("R2", 100, 7), Prioridad.REGULAR, 7);
        Solicitud temprano = new SolicitudDespegue(avion("R1", 100, 3), Prioridad.REGULAR, 3);
        assertTrue(temprano.compareTo(tarde) < 0, "Mayor espera = menor hora de solicitud");
    }

    @Test
    void ordenamientoCompletoCumpleLasCuatroClaves() {
        Solicitud r1 = new SolicitudDespegue(avion("R1", 100, 0), Prioridad.REGULAR, 0);
        Solicitud r2 = new SolicitudDespegue(avion("R2", 100, 4), Prioridad.REGULAR, 4);
        Solicitud cb = new SolicitudAterrizaje(avion("CB", 12, 6), Prioridad.COMBUSTIBLE_BAJO, 6);
        Solicitud em = new SolicitudAterrizaje(avion("EM", 60, 8), Prioridad.EMERGENCIA, 8);

        List<Solicitud> lista = new ArrayList<>(List.of(r2, cb, r1, em));
        Collections.sort(lista);
        assertEquals(List.of(em, cb, r1, r2), lista);
    }

    @Test
    void subirPrioridadNoPuedeBajarElNivel() {
        Solicitud s = new SolicitudAterrizaje(avion("X1", 50, 0), Prioridad.EMERGENCIA, 0);
        s.subirPrioridad(Prioridad.REGULAR);
        assertEquals(Prioridad.EMERGENCIA, s.getPrioridad());
    }

    @Test
    @Disabled("Progreso 2: implementar el heap binario (encolar/extraer con sift-up y sift-down)")
    void extraerDevuelveSiempreLaDeMayorPrioridad() {
        ColaPrioridad cola = new ColaPrioridad();
        Solicitud r = new SolicitudDespegue(avion("R", 100, 0), Prioridad.REGULAR, 0);
        Solicitud e = new SolicitudAterrizaje(avion("E", 100, 1), Prioridad.EMERGENCIA, 1);
        cola.encolar(r);
        cola.encolar(e);
        assertEquals(e, cola.extraer());
        assertEquals(r, cola.extraer());
        assertTrue(cola.estaVacia());
    }

    @Test
    @Disabled("Progreso 2: implementar reordenar() para cambios de prioridad")
    void reordenarReubicaUnaSolicitudPromovida() {
        ColaPrioridad cola = new ColaPrioridad();
        Solicitud r1 = new SolicitudDespegue(avion("R1", 100, 0), Prioridad.REGULAR, 0);
        Solicitud r2 = new SolicitudDespegue(avion("R2", 100, 1), Prioridad.REGULAR, 1);
        cola.encolar(r1);
        cola.encolar(r2);
        r2.subirPrioridad(Prioridad.EMERGENCIA);
        cola.reordenar(r2);
        assertEquals(r2, cola.verSiguiente());
    }
}
