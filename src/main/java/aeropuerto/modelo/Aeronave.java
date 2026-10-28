package aeropuerto.modelo;

/**
 * Aeronave que solicita pista (Módulo 1, con datos que usa el Módulo 4).
 *
 * <p>El combustible se expresa como <b>autonomía en minutos</b>. Como la autonomía
 * de todas las aeronaves en vuelo baja al mismo ritmo (1 minuto por minuto
 * simulado), el heap no se ordena por {@code combustibleRestante} (valor que cambia
 * con el tiempo), sino por {@link #horaAgotamiento()}, que es fijo mientras no se
 * reporte un nuevo nivel de combustible.</p>
 *
 * <p>Responsable: Nicole Chancusig.</p>
 */
public class Aeronave {

    private final String idVuelo;
    private final String aerolinea;
    private final String tipo;
    /** Autonomía restante en minutos, medida en {@link #horaReporteCombustible}. */
    private int combustibleRestante;
    /** Minuto simulado en el que se reportó {@link #combustibleRestante}. */
    private int horaReporteCombustible;

    public Aeronave(String idVuelo, String aerolinea, String tipo,
                    int combustibleRestante, int horaReporteCombustible) {
        this.idVuelo = idVuelo;
        this.aerolinea = aerolinea;
        this.tipo = tipo;
        this.combustibleRestante = combustibleRestante;
        this.horaReporteCombustible = horaReporteCombustible;
    }

    /**
     * Registra un nuevo reporte de combustible.
     * Si la aeronave está en la cola, quien llame debe invocar
     * {@code ColaPrioridad.reordenar(...)} porque cambia la clave del heap.
     *
     * @param minutos autonomía restante reportada
     * @param hora    minuto simulado del reporte
     */
    public void actualizarCombustible(int minutos, int hora) {
        this.combustibleRestante = minutos;
        this.horaReporteCombustible = hora;
    }

    /**
     * Minuto simulado en el que se agotaría el combustible.
     * Es la clave estática que usa el comparador en el nivel COMBUSTIBLE_BAJO.
     *
     * @return horaReporteCombustible + combustibleRestante
     */
    public int horaAgotamiento() {
        return horaReporteCombustible + combustibleRestante;
    }

    /**
     * Indica si la autonomía que le queda a la aeronave en {@code horaActual}
     * es menor que el umbral.
     *
     * @param umbral     minutos de autonomía bajo los cuales se considera combustible bajo
     * @param horaActual minuto simulado actual
     * @return {@code true} si {@code horaAgotamiento() - horaActual < umbral}
     */
    public boolean estaBajoUmbral(int umbral, int horaActual) {
        return horaAgotamiento() - horaActual < umbral;
    }

    public String getIdVuelo() { return idVuelo; }
    public String getAerolinea() { return aerolinea; }
    public String getTipo() { return tipo; }
    public int getCombustibleRestante() { return combustibleRestante; }
    public int getHoraReporteCombustible() { return horaReporteCombustible; }

    @Override
    public String toString() {
        return idVuelo + " (" + aerolinea + ", " + tipo + ")";
    }
}
