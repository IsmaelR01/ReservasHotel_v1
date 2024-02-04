package org.iesalandalus.programacion.reservashotel.negocio;

import org.iesalandalus.programacion.reservashotel.dominio.*;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.Arrays;

public class Reservas {
    public Reserva[] coleccionReservas;
    private int capacidad;
    private int tamano;

    public Reservas(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.coleccionReservas = new Reserva[capacidad];
        this.tamano = 0;
    }

    public Reserva[] get() {
        return copiaProfundaReservas();
    }

    private Reserva[] copiaProfundaReservas() {
        Reserva[] copiaReservas = new Reserva[tamano];
        for (int i = 0; i < tamano; i++) {
            copiaReservas[i] = new Reserva(coleccionReservas[i]);
        }
        return copiaReservas;
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void insertar(Reserva reserva) throws OperationNotSupportedException {
        if (reserva == null) {
            throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
        }
        int posicion = buscarIndice(reserva);
        if (capacidadSuperada(posicion)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más reservas.");
        }
        if (!tamanoSuperado(posicion)) {
            throw new OperationNotSupportedException("ERROR: Ya existe una reserva igual.");
        }
        if (tamanoSuperado(posicion)) {
            coleccionReservas[posicion] = new Reserva(reserva);
            tamano++;
        }
    }

    private int buscarIndice(Reserva reserva) {
        int indice = 0;
        for (int i = 0; i < tamano; i++) {
            if (coleccionReservas[i].equals(reserva)) {
                return i;
            }
            indice++;
        }
        return indice;
    }

    private boolean tamanoSuperado(int indice) {
        if (indice >= tamano) {
            return true;
        } else
            return false;
    }

    private boolean capacidadSuperada(int indice) {
        if (indice >= capacidad) {
            return true;
        } else
            return false;
    }

    public Reserva buscar(Reserva reserva) {
        int posicion = buscarIndice(reserva);
        if (tamanoSuperado(posicion)) {
            return null;
        }
        if (!tamanoSuperado(posicion)) {
            return coleccionReservas[posicion];
        }
        return reserva;
    }

    public void borrar(Reserva reserva) throws OperationNotSupportedException {

        if (reserva == null) {
            throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
        }
        int posicion = buscarIndice(reserva);

        if (tamanoSuperado(posicion)) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna reserva como la indicada.");
        }
        if (!tamanoSuperado(posicion)) {
            desplazarunaposicionHaciaIzquierda(posicion);
        }
    }

    private void desplazarunaposicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionReservas[i] = coleccionReservas[i + 1];
        }
        coleccionReservas[tamano - 1] = null;
        tamano--;
    }


    public Reserva[] getReservas(Huesped huesped) {
        if(huesped == null) {
            throw  new NullPointerException("ERROR: No se pueden buscar reservas de un huesped nulo.");
        }
        int contador = 0;
        for(int i= 0; i < tamano; i++) {
            if(coleccionReservas[i].getHuesped().equals(huesped)) {
                contador++;
            }
        }
        Reserva[] reservasHuesped = new Reserva[contador];
        contador = 0;
        for(int i = 0; i< tamano; i++) {
            if(coleccionReservas[i].getHuesped().equals(huesped)) {
                reservasHuesped[contador++] = coleccionReservas[i];
            }
        }
        return reservasHuesped;
    }

    public Reserva[] getReservas(TipoHabitacion tipoHabitacion) {
        if(tipoHabitacion == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un tipo de habitación nula.");
        }
        int contador = 0;
        for(int i = 0; i< tamano; i++) {
            if(coleccionReservas[i].getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
                contador++;
            }
        }
        Reserva[] reservasTipoHabitacion = new Reserva[contador];
        contador = 0;
        for(int i = 0; i< tamano; i++) {
            if(coleccionReservas[i].getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
                reservasTipoHabitacion[contador++] = coleccionReservas[i];
            }
        }
        return reservasTipoHabitacion;
    }

    public Reserva[] getReservasFuturas(Habitacion habitacion) {
        if(habitacion == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de una habitación nula.");
        }
        //tengo que saber cuantas reservas tiene una habitación
        /*
        int contador = 0;
        for(int i = 0; i< tamano; i++) {
            if(coleccionReservas[i].getHabitacion().equals(habitacion)) {
                contador++;
            }
        }

         */

        int contador = 0;
        for (int i = 0; i < tamano; i++) {
            if (coleccionReservas[i].getHabitacion().equals(habitacion) &&
                    coleccionReservas[i].getFechaInicioReserva().isAfter(LocalDate.now())) {
                contador++;
            }
        }

        Reserva[] reservasFuturas = new Reserva[contador];
        contador = 0;
        for (int i = 0; i < tamano; i++) {
            if (coleccionReservas[i].getHabitacion().equals(habitacion) &&
                    coleccionReservas[i].getFechaInicioReserva().isAfter(LocalDate.now())) {
                reservasFuturas[contador++] = coleccionReservas[i];
            }
        }
        return reservasFuturas;
    }

}


