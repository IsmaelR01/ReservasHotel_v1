package org.iesalandalus.programacion.reservashotel.vista;

public enum Opcion {

    SALIR("Salir"),
    INSERTAR_HUESPED("Insertar Hu�sped"),
    BUSCAR_HUESPED("Buscar Hu�sped"),
    BORRAR_HUESPED("Borrar Huesped"),
    MOSTRAR_HUESPEDES("Mostrar Hu�spedes"),
    INSERTAR_HABITACION("Insertar Habitacion"),
    BUSCAR_HABITACION("Buscar Habitacion"),
    BORRAR_HABITACION("Borrar Habitaci�n"),
    MOSTRAR_HABITACIONES("Mostrar Habitaciones"),
    INSERTAR_RESERVA("Insertar Reserva"),
    ANULAR_RESERVA("Anular Reserva"),
    MOSTRAR_RESERVAS("Mostrar Reservas"),
    CONSULTAR_DISPONIBILIDAD("Consultar Disponibilidad"),
    REALIZAR_CHECKIN("Realizar CheckIn"),
    REALIZAR_CHECKOUT("Realizar CheckOut");

    private String mensajeAMostrar;

    private Opcion(String mensajeAMostrar) {
        this.mensajeAMostrar = mensajeAMostrar;
    }

    public String toString() {

        return (ordinal() + 1) + ".- " + mensajeAMostrar;
    }
}
