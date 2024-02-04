package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;

public class Habitaciones {
    public Habitacion[] coleccionHabitaciones;
    private int capacidad;
    private int tamano;

    public Habitaciones(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.coleccionHabitaciones = new Habitacion[capacidad];
        this.tamano = 0;
    }

    public Habitacion[] get() {
        return copiaProfundaHabitaciones();
    }

    private Habitacion[] copiaProfundaHabitaciones() {
        Habitacion[] copiaHabitaciones = new Habitacion[tamano];
        for (int i = 0; i < tamano; i++) {
            copiaHabitaciones[i] = new Habitacion(coleccionHabitaciones[i]);
        }
        return copiaHabitaciones;
    }

    public Habitacion[] get(TipoHabitacion tipoHabitacion) {
        int contadorTipoHabitacion = 0;
        for (int i = 0; i < get().length; i++) {
            if(coleccionHabitaciones[i].getTipoHabitacion().equals(tipoHabitacion)) {
                contadorTipoHabitacion++;
            }
        }
        Habitacion[] listaTipoHabitacion = new Habitacion[contadorTipoHabitacion];
        int contador = 0;
        for(int i = 0;i< get().length; i++) {
            if(coleccionHabitaciones[i].getTipoHabitacion().equals(tipoHabitacion)) {
                listaTipoHabitacion[contador++] = coleccionHabitaciones[i];
            }
        }
        return listaTipoHabitacion;
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void insertar(Habitacion habitacion) throws OperationNotSupportedException {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se puede insertar una habitaci�n nula.");
        }
        int posicion = buscarIndice(habitacion);
        if (capacidadSuperada(posicion)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan m�s habitaciones.");
        }
        if (!tamanoSuperado(posicion)) {
            throw new OperationNotSupportedException("ERROR: Ya existe una habitaci�n con ese identificador.");
        }
        if (tamanoSuperado(posicion)) {
            coleccionHabitaciones[posicion] = new Habitacion(habitacion);
            tamano++;
        }
    }

    private int buscarIndice(Habitacion habitacion) {
        int indice = 0;
        for (int i = 0; i < tamano; i++) {
            if (coleccionHabitaciones[i].equals(habitacion)) {
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

    public Habitacion buscar(Habitacion habitacion) {
        int posicion = buscarIndice(habitacion);
        if (tamanoSuperado(posicion)) {
            return null;
        }
        if (!tamanoSuperado(posicion)) {
            return coleccionHabitaciones[posicion];
        }
        return habitacion;
    }

    public void borrar(Habitacion habitacion) throws OperationNotSupportedException {

        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se puede borrar una habitaci�n nula.");
        }
        int posicion = buscarIndice(habitacion);

        if (tamanoSuperado(posicion)) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna habitaci�n como la indicada.");
        }
        if (!tamanoSuperado(posicion)) {
            desplazarunaposicionHaciaIzquierda(posicion);
        }
    }

    private void desplazarunaposicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionHabitaciones[i] = coleccionHabitaciones[i + 1];
        }
        coleccionHabitaciones[tamano - 1] = null;
        tamano--;
    }
}
