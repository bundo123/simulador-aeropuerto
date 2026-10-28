# Simulador de gestión de pistas de un aeropuerto

**Materia:** Programación III · **Universidad:** Universidad de Las Américas (UDLA), Facultad de Ingeniería y Ciencias Aplicadas  
**Docente:** Byron Rodríguez · **NRC:** 2336  
**Entrega actual:** Progreso 1 – Análisis, diseño y selección de la solución  
**Lenguaje:** Java 17 (Maven)

## Integrantes

| Integrante | Responsabilidad principal |
|---|---|
| Nicole Chancusig | Módulo 1 y reportes del Módulo 5 |
| David Galarza | Módulo 2 y estadísticas/historial del Módulo 5 |
| Felipe Hurtado | Módulo 3 (módulo central) |
| Mateo Izquierdo | Módulo 4 |

## Descripción del problema

En un aeropuerto, la pista es el recurso más escaso y el más difícil de ampliar. Cuando varias aeronaves piden aterrizar o despegar al mismo tiempo, el controlador tiene que decidir el orden y respetar los tiempos mínimos de separación entre operaciones. Además, no todas las solicitudes tienen la misma urgencia: una emergencia o un vuelo con poco combustible no puede esperar igual que un vuelo regular. Si no hay un criterio sistemático, aumentan los retrasos, el consumo de combustible en espera y el riesgo operativo.

Este proyecto construye un **simulador en Java** que recibe solicitudes de forma dinámica, las ordena con una **cola de prioridad (heap binario)**, asigna pistas libres y compatibles, y mide los tiempos de espera. El objetivo no es resolver el problema óptimo de programación de pistas, sino gestionar las solicitudes con estructuras de datos adecuadas.

## Reglas del modelo (valores simulados, definidos por el equipo)

Todos los valores son configurables en [`config/parametros.properties`](config/parametros.properties).

- Una pista atiende una sola operación a la vez.
- **Separación mínima** (se mide entre el *inicio* de dos operaciones consecutivas en la misma pista): aterrizaje tras aterrizaje, 3 min; cualquier otra combinación, 2 min.
- **Precedencia:** emergencia → combustible bajo → regular.
- **Combustible bajo:** se aplica solo a **aterrizajes** con autonomía restante menor a **20 min**. Una aeronave que va a despegar está en tierra y no corre ese riesgo.
- **Desempate:** a igual prioridad, se atiende primero la solicitud con **menor hora de solicitud**, es decir, la que lleva más tiempo esperando.
- **Anti‑inanición (envejecimiento):** una solicitud regular que espera **15 min** o más (valor configurable) sube al nivel de combustible bajo. Nunca supera a una emergencia y conserva su prioridad original para las estadísticas.
- Las solicitudes llegan de forma dinámica: no se conocen todas desde el inicio.

## Estructuras de datos y algoritmos elegidos

**Alternativa seleccionada: A – cola de prioridad con heap binario.** La alternativa descartada fue B: tres colas FIFO, una por nivel, con envejecimiento.

El heap usa un **comparador compuesto de 4 claves** (`Solicitud.compareTo`). Ninguna de las claves cambia mientras la solicitud está dentro del heap. Eso importa, porque si una clave cambiara sin reubicar el elemento, el heap dejaría de estar ordenado:

1. Nivel de prioridad (EMERGENCIA > COMBUSTIBLE_BAJO > REGULAR).
2. Dentro de COMBUSTIBLE_BAJO: menor `horaAgotamiento()` primero (hora del último reporte + autonomía restante).
3. Menor `horaSolicitud` primero.
4. Menor número de secuencia, que corresponde al orden de llegada y garantiza un orden total y estable.

Cuando una clave sí cambia (se declara una emergencia, llega un nuevo reporte de combustible o actúa el envejecimiento), la solicitud se reubica con `ColaPrioridad.reordenar()`.

| Estructura | Uso | Operación | Complejidad temporal |
|---|---|---|---|
| Heap binario sobre arreglo (`ColaPrioridad`) | Solicitudes pendientes | encolar / extraer | O(log n) |
| | | ver siguiente | O(1) |
| | | cambiar prioridad / cancelar | O(n) localizar + O(log n) reubicar |
| Recorrido lineal de pistas | Asignación voraz | buscar pista libre compatible | O(p) |
| Pila enlazada (`PilaHistorial`) | Historial de operaciones | apilar / últimas k | O(1) / O(k) |
| `HashMap` (`RegistroSolicitudes`) | Búsqueda por vuelo | buscar / registrar | O(1) promedio |
| Merge sort | Reporte ordenado por espera | ordenar m operaciones | O(m log m) |

En la tabla, *n* es el número de solicitudes pendientes, *p* el número de pistas (pequeño y fijo) y *m* el número de operaciones completadas. La complejidad espacial total es O(n + m).

## Módulos funcionales

| Módulo | Descripción | Clases principales | Responsable |
|---|---|---|---|
| 1. Gestión de aeronaves y solicitudes | Registro, cancelación, búsqueda por vuelo, filtrado y clasificación automática | `Aeronave`, `Solicitud`, `SolicitudAterrizaje`, `SolicitudDespegue`, `RegistroSolicitudes` | Nicole Chancusig |
| 2. Gestión de pistas | Estado de cada pista, separación mínima, búsqueda de pista libre compatible, cierre y liberación | `Pista` | David Galarza |
| 3. Cola de prioridad y asignación (central) | Heap binario con comparador compuesto, asignación voraz y envejecimiento | `ColaPrioridad`, `Asignacion`, `Aeropuerto` | Felipe Hurtado |
| 4. Emergencias y control de combustible | Declaración de emergencias, detección de combustible bajo, escalamiento de prioridad y alertas | `ControlCombustible`, `Alerta` | Mateo Izquierdo |
| 5. Estadísticas, historial y reportes | Espera promedio y máxima, historial (pila) y reportes por prioridad y por pista | `HistorialOperaciones`, `PilaHistorial`, `Estadisticas` | Nicole Chancusig y David Galarza |

La interfaz de consola (`MenuConsola`) y las pruebas de integración se reparten entre todo el equipo durante la fase de integración.

## Diagrama de clases

El diagrama está en [`docs/uml/diagrama-clases.puml`](docs/uml/diagrama-clases.puml) (código fuente en PlantUML) y en sus versiones exportadas [PNG](docs/uml/diagrama-clases.png) y [SVG](docs/uml/diagrama-clases.svg).

![Diagrama de clases](docs/uml/diagrama-clases.png)

## Estructura del repositorio

```
simulador-aeropuerto/
├── README.md
├── .gitignore
├── pom.xml
├── config/
│   └── parametros.properties
├── docs/
│   ├── informe/
│   │   └── Avance_Progreso1.pdf
│   ├── presentacion/
│   │   └── Presentacion_Progreso1.pdf
│   └── uml/
│       ├── diagrama-clases.puml
│       ├── diagrama-clases.png
│       └── diagrama-clases.svg
└── src/
    ├── main/java/aeropuerto/
    │   ├── Main.java
    │   ├── modelo/
    │   │   ├── Aeronave.java
    │   │   ├── Solicitud.java
    │   │   ├── SolicitudAterrizaje.java
    │   │   ├── SolicitudDespegue.java
    │   │   ├── Pista.java
    │   │   ├── Asignacion.java
    │   │   ├── Alerta.java
    │   │   └── enums/
    │   │       ├── Prioridad.java
    │   │       ├── TipoOperacion.java
    │   │       ├── EstadoPista.java
    │   │       └── TipoAlerta.java
    │   ├── estructuras/
    │   │   ├── ColaPrioridad.java
    │   │   └── PilaHistorial.java
    │   ├── servicio/
    │   │   ├── Aeropuerto.java
    │   │   ├── RegistroSolicitudes.java
    │   │   ├── ControlCombustible.java
    │   │   ├── HistorialOperaciones.java
    │   │   └── Estadisticas.java
    │   └── ui/
    │       └── MenuConsola.java
    └── test/java/aeropuerto/
        ├── ColaPrioridadTest.java
        ├── PistaTest.java
        └── AeropuertoTest.java
```

## Cómo compilar y ejecutar

Requisitos: JDK 17 o superior y Apache Maven 3.8 o superior.

```
mvn compile
mvn test
java -cp target/classes aeropuerto.Main
```

`mvn package` además genera `target/simulador-aeropuerto-0.1.0-PROGRESO1.jar`, que se ejecuta con `java -jar target/simulador-aeropuerto-0.1.0-PROGRESO1.jar`.

## Estado del proyecto

- **Progreso 1 (entregado):** análisis del problema, módulos, diagrama UML, comparación de alternativas y selección del heap binario. El código es un **esqueleto**: tiene las clases, los atributos y las firmas de los métodos según el UML. Por ahora solo están implementadas las reglas que definen el diseño: el comparador compuesto, la separación mínima y los cálculos de espera y de combustible, y todas ellas tienen pruebas que pasan. Los demás métodos lanzan `UnsupportedOperationException` con la nota *"Pendiente – Progreso 2"*, el nombre del responsable, el algoritmo previsto y su complejidad.
- **Progreso 2:** implementación del heap, la asignación, el envejecimiento, el historial, las estadísticas y el menú de consola, además de habilitar las pruebas marcadas con `@Disabled("Progreso 2")`.

## Declaración de uso de inteligencia artificial

Usamos herramientas de IA generativa como apoyo para estructurar el informe, revisar la coherencia entre los módulos, el UML y las alternativas, y generar el esqueleto inicial de clases. Todo el contenido fue revisado, adaptado y discutido por el equipo. El detalle (herramienta, uso, resultado incorporado y forma de verificación) está en la sección *Declaración de uso de herramientas de inteligencia artificial* del informe (`docs/informe/Avance_Progreso1.pdf`).

## Referencias principales

- Beasley, J. E., Krishnamoorthy, M., Sharaiha, Y. M., & Abramson, D. (2000). Scheduling aircraft landings—The static case. *Transportation Science, 34*(2), 180–197. https://doi.org/10.1287/trsc.34.2.180.12302
- Bennell, J. A., Mesgarpour, M., & Potts, C. N. (2011). Airport runway scheduling. *4OR, 9*(2), 115–138. https://doi.org/10.1007/s10288-011-0172-x
- Cormen, T. H., Leiserson, C. E., Rivest, R. L., & Stein, C. (2022). *Introduction to algorithms* (4th ed.). MIT Press.
- Shirini, K., Aghdasi, H. S., & Saeedvand, S. (2024). A comprehensive survey on multiple-runway aircraft landing optimization problem. *International Journal of Aeronautical and Space Sciences, 25*(4), 1574–1602. https://doi.org/10.1007/s42405-024-00747-z

La lista completa en formato APA 7 está en el informe.
