package org.iesalandalus.programacion.reservashotel.vista;

public enum Opcion {

    SALIR("Salir"),
    INSERTAR_HUESPED("Insertar Huésped"),
    BUSCAR_HUESPED("Buscar Huésped"),
    BORRAR_HUESPED("Borrar Huesped"),
    MOSTRAR_HUESPEDES("Mostrar Huéspedes"),
    INSERTAR_HABITACION("Insertar Habitacion"),
    BUSCAR_HABITACION("Buscar Habitacion"),
    BORRAR_HABITACION("Borrar Habitación"),
    MOSTRAR_HABITACIONES("Mostrar Habitaciones"),
    INSERTAR_RESERVA("Insertar Reserva"),
    ANULAR_RESERVA("Anular Reserva"),
    MOSTRAR_RESERVAS("Mostrar Reservas"),
    CONSULTAR_DISPONIBILIDAD("Consultar Disponibilidad");

    private String mensajeAMostrar;

    private Opcion(String mensajeAMostrar) {
        this.mensajeAMostrar = mensajeAMostrar;
    }

    public String toString() {

        return (ordinal() + 1) + ".- " + mensajeAMostrar;
    }
}
