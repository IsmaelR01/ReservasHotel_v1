package org.iesalandalus.programacion.reservashotel;

import org.iesalandalus.programacion.reservashotel.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.negocio.Habitaciones;
import org.iesalandalus.programacion.reservashotel.negocio.Huespedes;
import org.iesalandalus.programacion.reservashotel.negocio.Reservas;
import org.iesalandalus.programacion.reservashotel.vista.Consola;
import org.iesalandalus.programacion.reservashotel.vista.Opcion;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Comparator;

public class MainApp {
    private static Huespedes huespedes;
    private static Habitaciones habitaciones;
    private static Reservas reservas;
    public static final int CAPACIDAD = 10;

    public static void main(String[] args) {
        Opcion opcion;
        huespedes = new Huespedes(CAPACIDAD);
        habitaciones = new Habitaciones(CAPACIDAD);
        reservas = new Reservas(CAPACIDAD);

        do {
            Consola.mostrarMenu();
            ejecutarOpcion(opcion = Consola.elegirOpcion());
        }while(opcion != Opcion.SALIR);

    }

    private static void ejecutarOpcion(Opcion opcion) {
        switch (opcion) {
            case SALIR:
                System.out.println("Adios, hasta luego");
                break;
            case INSERTAR_HUESPED:
                insertarHuesped();
                break;
            case BUSCAR_HUESPED:
                buscarHuesped();
                break;
            case BORRAR_HUESPED:
                borrarHuesped();
                break;
            case MOSTRAR_HUESPEDES:
                mostrarHuespedes();
                break;
            case INSERTAR_HABITACION:
                insertarHabitacion();
                break;
            case BUSCAR_HABITACION:
                buscarHabitacion();
                break;
            case BORRAR_HABITACION:
                borrarHabitacion();
                break;
            case MOSTRAR_HABITACIONES:
                mostrarHabitaciones();
                break;
            case INSERTAR_RESERVA:
                insertarReserva();
                break;
            case ANULAR_RESERVA:
                anularReserva();
                break;
            case MOSTRAR_RESERVAS:
                mostrarReservas();
                break;
            case CONSULTAR_DISPONIBILIDAD:
                consultarDisponibilidad(Consola.leerTipoHabitacion(),Consola.leerfecha("Introduce fecha inicio reserva formato (dd/MM/yyyy)"),Consola.leerfecha("Introduce fecha fin reserva formato (dd/MM/yyyy)"));

            default:
                System.out.println("Opción no valida inténtalo de nuevo");

        }

    }



    private static void insertarHuesped() {
        try {
            huespedes.insertar(Consola.leerHuesped());
            System.out.println("Huésped insertado correctamente.");
        }catch(NullPointerException | IllegalArgumentException | OperationNotSupportedException |
               DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void buscarHuesped() {
        try {
            Huesped huesped = Consola.getHuespedPorDni();
            System.out.println(huespedes.buscar(huesped));
        }catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void borrarHuesped() {
        try {
            huespedes.borrar(Consola.getHuespedPorDni());
            System.out.println("Huesped borrado correctamente.");
        }catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarHuespedes() {
        if(huespedes.getTamano() == 0) {
            System.out.println("No hay huéspedes para mostrar.");
        }else{
            for(int i = 0; i<huespedes.get().length;i++) {
                System.out.println(huespedes.get()[i].toString());
            }
        }
    }

    private static void insertarHabitacion() {
        try {
            habitaciones.insertar(Consola.leerHabitacion());
            System.out.println("Habitación insertada correctamente");
        } catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void buscarHabitacion() {
        try {
            Habitacion habitacion = Consola.leerHabitacionPorIdentificador();
            System.out.println(habitaciones.buscar(habitacion));
        }catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void borrarHabitacion() {
        try {
            habitaciones.borrar(Consola.leerHabitacionPorIdentificador());
            System.out.println("Habitación borrada correctamente.");
        }catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarHabitaciones() {
        if(habitaciones.getTamano() == 0) {
            System.out.println("No hay habitaciones para mostrar.");
        }else{
            for(int i = 0; i<habitaciones.get().length;i++) {
                System.out.println(habitaciones.get()[i].toString());
            }
        }
    }

    private static void insertarReserva() {
        try {
            Reserva reserva = Consola.leerReserva();
            System.out.println("Ahora consulta disponibilidad");
            if(consultarDisponibilidad(reserva.getHabitacion().getTipoHabitacion(),reserva.getFechaInicioReserva(),reserva.getFechaFinReserva()) == null) {
                System.out.println("La habitación no está disponible");
            }else{
                System.out.println("Hay habitaciones disponibles, se puede insertar la reserva.");
                reservas.insertar(reserva);
                System.out.println("Reserva insertada correctamente.");
            }

        }catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException | DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarReservas(Huesped huesped) {
        if(huesped == null) {
            System.out.println("El huésped no puede ser nulo.");
        }
        try {
            Reserva[] reservasHuesped = reservas.getReservas(huesped);
            if (reservasHuesped.length == 0) {
                System.out.println("El huésped no tiene reservas.");
            } else {
                System.out.println("Reservas del huésped " + huesped + ":");
                System.out.println(Arrays.toString(reservasHuesped));
            }
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarReservas(TipoHabitacion tipoHabitacion) {
        if(tipoHabitacion == null) {
            System.out.println("El tipo de habitación no puede ser nulo");
        }
        try {
            Reserva[] reservasTipoHabitacion = reservas.getReservas(tipoHabitacion);
            if (reservasTipoHabitacion.length == 0) {
                System.out.println("No hay reservas para el tipo de habitación " + tipoHabitacion + ".");
            } else {
                System.out.println("Reservas de habitaciones tipo " + tipoHabitacion + ":");
                System.out.println(Arrays.toString(reservasTipoHabitacion));
            }
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    private static Reserva[] getReservasAnulables(Reserva[] reservaAAnular) {
        int contadorReservasAnulables = 0;

        for(Reserva reserva : reservaAAnular) {
            if(reserva.getFechaInicioReserva().isAfter(LocalDate.now())) {
                contadorReservasAnulables++;
            }
        }

        Reserva[] reservasAnulables = new Reserva[contadorReservasAnulables];
        int contador = 0;

        for (int i = 0; i < reservasAnulables.length; i++) {
            Reserva reserva = reservaAAnular[i];
            if (reserva.getFechaInicioReserva().isAfter(LocalDate.now())) {
                reservasAnulables[contador++] = reserva;
            }
        }
        return reservasAnulables;
    }

    private static void anularReserva() {
        try {
            Huesped huesped = Consola.getHuespedPorDni();
            Reserva[] reservasHuesped = reservas.getReservas(huesped);

            Reserva[] reservasAnulables = getReservasAnulables(reservasHuesped);
            if (reservasAnulables.length == 0) {
                System.out.println("El huésped no tiene reservas anulables.");
            }
                System.out.println("Seleccione la reserva que desea anular:");
                for (int i = 0; i < reservasAnulables.length; i++) {
                    System.out.println((i + 1) + ".-" + reservasAnulables[i].toString());
                }

                int opcion;
                do {
                    System.out.println("Ingrese el número de la reserva a anular:");
                    opcion = Entrada.entero();
                    if (opcion < 1 || opcion > reservasAnulables.length) {
                        System.out.println("Opción incorrecta, inténtalo de nuevo.");
                    }
                } while (opcion < 1 || opcion > reservasAnulables.length);


                char respuesta;
                do {
                    System.out.println("¿Está seguro de que desea anular la reserva? (s/n)");
                    respuesta = Character.toLowerCase(Entrada.caracter());
                    if (respuesta != 's' && respuesta != 'n') {
                        System.out.println("Respuesta incorrecta, por favor ingrese 's' o 'n'.");
                    }
                } while (respuesta != 's' && respuesta != 'n');

                if (respuesta == 's') {
                    reservas.borrar(reservasAnulables[opcion - 1]);
                    System.out.println("Reserva anulada correctamente.");
                } else {
                    System.out.println("Anulación de reserva cancelada.");
                }

        } catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarReservas() {
        if(reservas.getTamano() == 0) {
            System.out.println("No hay reservas para mostrar.");
        }else{
            for(int i = 0; i<reservas.get().length;i++) {
                System.out.println(reservas.get()[i].toString());
            }
        }
    }

    private static int getNumElementosNoNulos(Reserva[] reservasFuturas) {
        int contador = 0;
        for(int i = 0; i < reservasFuturas.length; i++) {
            if(reservasFuturas[i]!= null) {
                contador++;
            }
        }
        return contador;
    }


    private static Habitacion consultarDisponibilidad(TipoHabitacion tipoHabitacion, LocalDate fechaInicioReserva, LocalDate fechaFinReserva)
    {
        boolean tipoHabitacionEncontrada=false;
        Habitacion habitacionDisponible=null;
        int numElementos=0;

        Habitacion[] habitacionesTipoSolicitado= habitaciones.get(tipoHabitacion);

        if (habitacionesTipoSolicitado==null)
            return habitacionDisponible;

        for (int i=0; i<habitacionesTipoSolicitado.length && !tipoHabitacionEncontrada; i++)
        {

            if (habitacionesTipoSolicitado[i]!=null)
            {
                Reserva[] reservasFuturas = reservas.getReservasFuturas(habitacionesTipoSolicitado[i]);
                numElementos=getNumElementosNoNulos(reservasFuturas);

                if (numElementos == 0)
                {
                    //Si la primera de las habitaciones encontradas del tipo solicitado no tiene reservas en el futuro,
                    // quiere decir que está disponible.
                    habitacionDisponible=new Habitacion(habitacionesTipoSolicitado[i]);
                    tipoHabitacionEncontrada=true;
                }
                else {

                    //Ordenamos de mayor a menor las reservas futuras encontradas por fecha de fin de la reserva.
                    // Si la fecha de inicio de la reserva es posterior a la mayor de las fechas de fin de las reservas
                    // (la reserva de la posición 0), quiere decir que la habitación está disponible en las fechas indicadas.

                    Arrays.sort(reservasFuturas, 0, numElementos, Comparator.comparing(Reserva::getFechaFinReserva).reversed());

                    /*System.out.println("\n\nMostramos las reservas ordenadas por fecha de inicio de menor a mayor (numelementos="+numElementos+")");
                    mostrar(reservasFuturas);*/

                    if (fechaInicioReserva.isAfter(reservasFuturas[0].getFechaFinReserva())) {
                        habitacionDisponible = new Habitacion(habitacionesTipoSolicitado[i]);
                        tipoHabitacionEncontrada = true;
                    }

                    if (!tipoHabitacionEncontrada)
                    {
                        //Ordenamos de menor a mayor las reservas futuras encontradas por fecha de inicio de la reserva.
                        // Si la fecha de fin de la reserva es anterior a la menor de las fechas de inicio de las reservas
                        // (la reserva de la posición 0), quiere decir que la habitación está disponible en las fechas indicadas.

                        Arrays.sort(reservasFuturas, 0, numElementos, Comparator.comparing(Reserva::getFechaInicioReserva));

                        /*System.out.println("\n\nMostramos las reservas ordenadas por fecha de inicio de menor a mayor (numelementos="+numElementos+")");
                        mostrar(reservasFuturas);*/

                        if (fechaFinReserva.isBefore(reservasFuturas[0].getFechaInicioReserva())) {
                            habitacionDisponible = new Habitacion(habitacionesTipoSolicitado[i]);
                            tipoHabitacionEncontrada = true;
                        }
                    }

                    //Recorremos el array de reservas futuras para ver si las fechas solicitadas están algún hueco existente entre las fechas reservadas
                    if (!tipoHabitacionEncontrada)
                    {
                        for(int j=1;j<reservasFuturas.length && !tipoHabitacionEncontrada;j++)
                        {
                            if (reservasFuturas[j]!=null && reservasFuturas[j-1]!=null)
                            {
                                if(fechaInicioReserva.isAfter(reservasFuturas[j-1].getFechaFinReserva()) &&
                                        fechaFinReserva.isBefore(reservasFuturas[j].getFechaInicioReserva())) {

                                    habitacionDisponible = new Habitacion(habitacionesTipoSolicitado[i]);
                                    tipoHabitacionEncontrada = true;
                                }
                            }
                        }
                    }


                }
            }
        }

        return habitacionDisponible;
    }

}

