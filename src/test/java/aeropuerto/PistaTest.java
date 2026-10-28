package aeropuerto;

import aeropuerto.modelo.Aeronave;
import aeropuerto.modelo.Asignacion;
import aeropuerto.modelo.Pista;
import aeropuerto.modelo.Solicitud;
import aeropuerto.modelo.SolicitudAterrizaje;
import aeropuerto.modelo.SolicitudDespegue;
import aeropuerto.modelo.enums.EstadoPista;
import aeropuerto.modelo.enums.Prioridad;
import aeropuerto.modelo.enums.TipoOperacion;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Pruebas de la regla de separación (implementada en Progreso 1 mediante
 * polimorfismo) y de la pista (deshabilitadas hasta Progreso 2).
 */
class PistaTest {

    private final Aeronave avion = new Aeronave("UD101", "Aerolinea", "A320", 60, 0);

    @Test
    void aterrizajeTrasAterrizajeRequiereTresMinutos() {
        Solicitud aterrizaje = new SolicitudAterrizaje(avion, Prioridad.REGULAR, 0);
        assertEquals(3, aterrizaje.separacionRequerida(TipoOperacion.ATERRIZAJE));
    }

    @Test
    void aterrizajeTrasDespegueRequiereDosMinutos() {
        Solicitud aterrizaje = new SolicitudAterrizaje(avion, Prioridad.REGULAR, 0);
        assertEquals(2, aterrizaje.separacionRequerida(TipoOperacion.DESPEGUE));
    }

    @Test
    void despegueRequiereDosMinutosTrasCualquierOperacion() {
        Solicitud despegue = new SolicitudDespegue(avion, Prioridad.REGULAR, 0);
        assertEquals(2, despegue.separacionRequerida(TipoOperacion.ATERRIZAJE));
        assertEquals(2, despegue.separacionRequerida(TipoOperacion.DESPEGUE));
    }

    @Test
    void pistaNuevaEmpiezaLibreYSinOperaciones() {
        Pista pista = new Pista(1);
        assertEquals(EstadoPista.LIBRE, pista.getEstado());
        assertEquals(null, pista.getUltimaOperacion());
    }

    @Test
    @Disabled("Progreso 2: implementar ocupar(), estaDisponible() y horaDisponible()")
    void pistaRespetaLaSeparacionMinima() {
        Pista pista = new Pista(1);
        Solicitud primero = new SolicitudAterrizaje(avion, Prioridad.REGULAR, 0);
        pista.ocupar(new Asignacion(primero, pista, 10, 12));
        Solicitud segundo = new SolicitudAterrizaje(
                new Aeronave("UD102", "Aerolinea", "A320", 60, 0), Prioridad.REGULAR, 0);
        assertFalse(pista.estaDisponible(12, segundo)); // 10 + 3 = 13
        assertEquals(13, pista.horaDisponible(segundo));
    }

    @Test
    @Disabled("Progreso 2: implementar cerrar()")
    void pistaCerradaNoRecibeAsignaciones() {
        Pista pista = new Pista(1);
        pista.cerrar();
        assertFalse(pista.estaDisponible(100, new SolicitudDespegue(avion, Prioridad.REGULAR, 0)));
    }
}
