package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.reservashotel.modelo.Modelo.*;

import static org.iesalandalus.programacion.reservashotel.modelo.Modelo.habitaciones;
import static org.iesalandalus.programacion.reservashotel.modelo.Modelo.huespedes;

public class Consola {
    private Consola() {

    }

    public static void mostrarMenu() {
        System.out.println("Menu de Opciones");
        Opcion[] opciones = Opcion.values();
        for (int i = 0; i < opciones.length; i++) {
            System.out.println(opciones[i].toString());
        }
    }

    public static Opcion elegirOpcion() {
        int opcionEscogida;
        do {
            System.out.println("Elija  una opción válida");
            opcionEscogida = Entrada.entero();
            if (opcionEscogida < 1 || opcionEscogida > Opcion.values().length) {
                System.out.println("Opción incorrecta, inténtalo de nuevo.");
            }
        } while (opcionEscogida < 1 || opcionEscogida > Opcion.values().length);
        return switch (opcionEscogida) {
            case 1 -> Opcion.SALIR;
            case 2 -> Opcion.INSERTAR_HUESPED;
            case 3 -> Opcion.BUSCAR_HUESPED;
            case 4 -> Opcion.BORRAR_HUESPED;
            case 5 -> Opcion.MOSTRAR_HUESPEDES;
            case 6 -> Opcion.INSERTAR_HABITACION;
            case 7 -> Opcion.BUSCAR_HABITACION;
            case 8 -> Opcion.BORRAR_HABITACION;
            case 9 -> Opcion.MOSTRAR_HABITACIONES;
            case 10 -> Opcion.INSERTAR_RESERVA;
            case 11 -> Opcion.ANULAR_RESERVA;
            case 12 -> Opcion.MOSTRAR_RESERVAS;
            case 13 -> Opcion.CONSULTAR_DISPONIBILIDAD;
            case 14 -> Opcion.REALIZAR_CHECKIN;
            case 15 -> Opcion.REALIZAR_CHECKOUT;
            default -> null;
        };
    }

    public static Huesped leerHuesped() {
        System.out.println("Introduce los datos del huésped");
        System.out.println("Nombre");
        String nombre = Entrada.cadena();
        System.out.println("DNI");
        String dni = Entrada.cadena();
        System.out.println("Correo electrónico");
        String correo = Entrada.cadena();
        System.out.println("Número de teléfono");
        String telefono = Entrada.cadena();
        System.out.println("Fecha de nacimiento formato (dd/MM/YYYY)");
        String fechaNacimientoCad = Entrada.cadena();
        LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoCad, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return new Huesped(nombre, dni, correo, telefono, fechaNacimiento);
    }

    public static Huesped getHuespedPorDni() {
        System.out.println("Introduce el dni del huésped");
        String dni = Entrada.cadena();
        Huesped[] busquedaHuesped = huespedes.get(); // Obtener el array de huéspedes
        for (int i = 0; i < busquedaHuesped.length; i++) {
            Huesped huesped = busquedaHuesped[i];
            if (huesped.getDni().equals(dni)) {
                return new Huesped(huesped.getNombre(), dni, huesped.getCorreo(), huesped.getTelefono(), huesped.getFechaNacimiento());
            }
        }
        return new Huesped("Spiderman Rodríguez Cabrera",dni,"spidermanr46@gmail.com","675679876",LocalDate.of(2001,5,23));
    }


    public static LocalDate leerfecha(String mensaje) {
        String fecha;
        do {
            System.out.println(mensaje);
            fecha = Entrada.cadena();
            return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } while (fecha == null);
    }

    public static LocalDateTime leerFechaHora(String mensaje) {
        String fecha;
        do {
            System.out.println(mensaje);
            fecha = Entrada.cadena();
            return LocalDateTime.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        }while(fecha == null);
    }


    public static Habitacion leerHabitacion() {

        System.out.println("Introduce los datos de la habitación");
        System.out.println("Planta");
        int planta = Entrada.entero();
        System.out.println("Puerta");
        int puerta = Entrada.entero();
        System.out.println("Precio");
        double precio = Entrada.realDoble();
        return new Habitacion(planta, puerta, precio, leerTipoHabitacion());
    }


    public static Habitacion leerHabitacionPorIdentificador() {
            System.out.println("Introduce el numero de planta");
            int planta = Entrada.entero();
            System.out.println("Introduce el número de puerta");
            int puerta = Entrada.entero();
            String identificador = "" + planta + puerta;
        Habitacion[] busquedaHabitacion = habitaciones.get();
        for (int i = 0; i < busquedaHabitacion.length; i++) {
            Habitacion habitacion = busquedaHabitacion[i];
            if(habitacion.getIdentificador().equals(identificador)) {
                return new Habitacion(planta,puerta,habitacion.getPrecio(),habitacion.getTipoHabitacion());
            }
        }
        return new Habitacion(planta,puerta,145,TipoHabitacion.DOBLE);
    }

    public static TipoHabitacion leerTipoHabitacion() {
        int opcionEscogida;
        do {
            System.out.println("Menu de Opciones Tipos de Habitación");
            TipoHabitacion[] tipoHabitacion = TipoHabitacion.values();
            for (int i = 0; i < tipoHabitacion.length; i++) {
                System.out.println(tipoHabitacion[i].toString());
            }
            System.out.println("Elija un tipo de habitación");
            opcionEscogida = Entrada.entero();
            if (opcionEscogida < 1 || opcionEscogida > Opcion.values().length) {
                System.out.println("Opción incorrecta, inténtalo de nuevo.");
            }
        } while (opcionEscogida < 1 || opcionEscogida > Opcion.values().length);
        return switch (opcionEscogida) {
            case 1 -> TipoHabitacion.SUITE;
            case 2 -> TipoHabitacion.SIMPLE;
            case 3 -> TipoHabitacion.DOBLE;
            case 4 -> TipoHabitacion.TRIPLE;
            default -> null;
        };
    }


    public static Regimen leerRegimen() {
        int opcionEscogida;
        do {
            System.out.println("Menu de Opciones Régimen");
            Regimen[] regimen = Regimen.values();
            for (int i = 0; i < regimen.length; i++) {
                System.out.println(regimen[i].toString());
            }
            System.out.println("Elija un Régimen");
            opcionEscogida = Entrada.entero();
            if (opcionEscogida < 1 || opcionEscogida > Opcion.values().length) {
                System.out.println("Opción incorrecta, inténtalo de nuevo.");
            }
        } while (opcionEscogida < 1 || opcionEscogida > Opcion.values().length);
        return switch (opcionEscogida) {
            case 1 -> Regimen.SOLO_ALOJAMIENTO;
            case 2 -> Regimen.ALOJAMIENTO_DESAYUNO;
            case 3 -> Regimen.MEDIA_PENSION;
            case 4 -> Regimen.PENSION_COMPLETA;
            default -> null;
        };
    }

    public static Reserva leerReserva() {
        System.out.println("Introduce el número de personas");
        int numPersonas = Entrada.entero();

        return new Reserva(getHuespedPorDni(), leerHabitacionPorIdentificador(), leerRegimen(), leerfecha("Introduce fecha inicio reserva formato (dd/MM/YYYY)"), leerfecha("Introduce la fecha de fin de la reserva formato (dd/MM/YYYY) "),numPersonas);
    }
}

