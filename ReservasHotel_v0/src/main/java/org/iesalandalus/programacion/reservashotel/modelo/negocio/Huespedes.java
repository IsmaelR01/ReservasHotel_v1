package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;

import javax.naming.OperationNotSupportedException;

public class Huespedes {
    public Huesped[] coleccionHuespedes;
    private int capacidad;
    private int tamano;

    public Huespedes(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.coleccionHuespedes = new Huesped[capacidad];
        this.tamano = 0;
    }

    public Huesped[] get() {
        return copiaProfundaHuespedes();
    }

    private Huesped[] copiaProfundaHuespedes() {
        Huesped[] copiaHuespedes = new Huesped[tamano];
        for (int i = 0; i < tamano; i++) {
            copiaHuespedes[i] = new Huesped(coleccionHuespedes[i]);
        }
        return copiaHuespedes;
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void insertar(Huesped huesped) throws OperationNotSupportedException {
        if (huesped == null) {
            throw new NullPointerException("ERROR: No se puede insertar un hu�sped nulo.");
        }
        int posicion = buscarIndice(huesped);
        if (capacidadSuperada(posicion)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan m�s hu�spedes.");
        }
        if (!tamanoSuperado(posicion)) {
            throw new OperationNotSupportedException("ERROR: Ya existe un hu�sped con ese dni.");
        }
        if (tamanoSuperado(posicion)) {
            coleccionHuespedes[posicion] = new Huesped(huesped);
            tamano++;
        }
    }

    private int buscarIndice(Huesped huesped) {
        int indice = 0;
        for (int i = 0; i < tamano; i++) {
            if (coleccionHuespedes[i].equals(huesped)) {
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

    public Huesped buscar(Huesped huesped) {
        int posicion = buscarIndice(huesped);
        if (tamanoSuperado(posicion)) {
            return null;
        }
        if (!tamanoSuperado(posicion)) {
            return coleccionHuespedes[posicion];
        }
        return huesped;
    }

    public void borrar(Huesped huesped) throws OperationNotSupportedException {

        if (huesped == null) {
            throw new NullPointerException("ERROR: No se puede borrar un hu�sped nulo.");
        }
        int posicion = buscarIndice(huesped);

        if (tamanoSuperado(posicion)) {
            throw new OperationNotSupportedException("ERROR: No existe ning�n hu�sped como el indicado.");
        }
        if (!tamanoSuperado(posicion)) {
            desplazarunaposicionHaciaIzquierda(posicion);
        }
    }

    private void desplazarunaposicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionHuespedes[i] = coleccionHuespedes[i + 1];
        }
        coleccionHuespedes[tamano - 1] = null;
        tamano--;
    }

}
