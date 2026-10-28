package aeropuerto;

import aeropuerto.modelo.Aeronave;
import aeropuerto.modelo.Solicitud;
import aeropuerto.modelo.SolicitudAterrizaje;
import aeropuerto.modelo.SolicitudDespegue;
import aeropuerto.modelo.enums.Prioridad;
import aeropuerto.servicio.Aeropuerto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Pruebas del controlador y de las reglas de dominio ya definidas en Progreso 1.
 * Las pruebas de integración quedan deshabilitadas hasta Progreso 2.
 */
class AeropuertoTest {

    @Test
    void elAeropuertoSeCreaConLasPistasConfiguradas() {
        Aeropuerto aeropuerto = new Aeropuerto(2, 20, 15);
        assertEquals(2, aeropuerto.getPistas().size());
        assertTrue(aeropuerto.getCola().estaVacia());
        assertEquals(0, aeropuerto.getHoraActual());
    }

    @Test
    void horaAgotamientoEsUnaClaveFija() {
        Aeronave a = new Aeronave("UD200", "Aerolinea", "E190", 25, 10);
        assertEquals(35, a.horaAgotamiento());
    }

    @Test
    void detectaCombustibleBajoSegunElUmbral() {
        Aeronave a = new Aeronave("UD201", "Aerolinea", "E190", 30, 0); // se agota en t = 30
        assertFalse(a.estaBajoUmbral(20, 5));  // le quedan 25 min
        assertTrue(a.estaBajoUmbral(20, 15));  // le quedan 15 min
    }

    @Test
    void calculaElTiempoDeEspera() {
        Solicitud s = new SolicitudDespegue(new Aeronave("UD202", "Aerolinea", "A320", 90, 0),
                Prioridad.REGULAR, 12);
        assertEquals(8, s.calcularEspera(20));
    }

    @Test
    void conservaLaPrioridadOriginalParaEstadisticas() {
        Solicitud s = new SolicitudAterrizaje(new Aeronave("UD203", "Aerolinea", "A320", 90, 0),
                Prioridad.REGULAR, 0);
        s.subirPrioridad(Prioridad.COMBUSTIBLE_BAJO);
        assertEquals(Prioridad.COMBUSTIBLE_BAJO, s.getPrioridad());
        assertEquals(Prioridad.REGULAR, s.getPrioridadOriginal());
    }

    @Test
    @Disabled("Progreso 2: implementar recibirSolicitud() y asignarSiguiente()")
    void asignaPrimeroLaEmergencia() {
        Aeropuerto aeropuerto = new Aeropuerto(1, 20, 15);
        Solicitud regular = new SolicitudDespegue(new Aeronave("R", "X", "A320", 90, 0), Prioridad.REGULAR, 0);
        Solicitud emergencia = new SolicitudAterrizaje(new Aeronave("E", "X", "A320", 90, 0), Prioridad.EMERGENCIA, 0);
        aeropuerto.recibirSolicitud(regular);
        aeropuerto.recibirSolicitud(emergencia);
        assertEquals(emergencia, aeropuerto.asignarSiguiente().getSolicitud());
    }

    @Test
    @Disabled("Progreso 2: implementar aplicarEnvejecimiento()")
    void unaSolicitudRegularQueSuperaElLimiteSubeDeNivel() {
        Aeropuerto aeropuerto = new Aeropuerto(1, 20, 15);
        Solicitud regular = new SolicitudDespegue(new Aeronave("R", "X", "A320", 90, 0), Prioridad.REGULAR, 0);
        aeropuerto.recibirSolicitud(regular);
        aeropuerto.avanzarTiempo(15);
        assertEquals(Prioridad.COMBUSTIBLE_BAJO, regular.getPrioridad());
    }
}
