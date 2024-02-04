package org.iesalandalus.programacion.reservashotel.modelo.dominio;

import java.util.Objects;

public class Habitacion {
    public static final double MIN_PRECIO_HABITACION = 40;
    public static final double MAX_PRECIO_HABITACION = 150;
    public static final int MIN_NUMERO_PUERTA = 0;
    public static final int MAX_NUMERO_PUERTA = 15;
    public static final int MIN_NUMERO_PLANTA = 0;
    public static final int MAX_NUMERO_PLANTA = 3;
    private String identificador;
    private int planta;
    private int puerta;
    private double precio;
    private TipoHabitacion tipoHabitacion;

    public Habitacion(int planta, int puerta, double precio) {
        setPlanta(planta);
        setPuerta(puerta);
        setIdentificador();
        setPrecio(precio);
        tipoHabitacion = TipoHabitacion.SIMPLE;
    }
    public Habitacion(int planta, int puerta, double precio, TipoHabitacion tipoHabitacion) {
        setPlanta(planta);
        setPuerta(puerta);
        setIdentificador();
        setPrecio(precio);
        setTipoHabitacion(tipoHabitacion);
    }

    public Habitacion(Habitacion habitacion) {
        if(habitacion == null) {
            throw new NullPointerException("ERROR: No es posible copiar una habitaci?n nula.");
        }
        setPlanta(habitacion.getPlanta());
        setPuerta(habitacion.getPuerta());
        setPrecio(habitacion.getPrecio());
        setTipoHabitacion(habitacion.getTipoHabitacion());
        setIdentificador(habitacion.getIdentificador());
    }



    public String getIdentificador() {
        return identificador;
    }
    private void setIdentificador() {
        this.identificador = (this.planta) + String.valueOf(this.puerta);
    }
    private void setIdentificador (String identificador) {
        if(identificador == null) {
            throw new NullPointerException("ERROR: El identificador no puede ser nulo.");
        }
        this.identificador = identificador;
    }
    public int getPlanta() {
        return planta;
    }
    private void setPlanta(int planta) {
        if(planta <= MIN_NUMERO_PLANTA || planta > MAX_NUMERO_PLANTA) {
            throw new IllegalArgumentException("ERROR: No se puede establecer como planta de una habitaci?n un valor menor que " + MIN_NUMERO_PLANTA + " ni mayor que " + MAX_NUMERO_PLANTA + ".");
        }
        this.planta = planta;
    }
    public int getPuerta() {
        return puerta;
    }
    private void setPuerta(int puerta) {
        if(puerta < MIN_NUMERO_PUERTA || puerta >= MAX_NUMERO_PUERTA) {
            throw new IllegalArgumentException("ERROR: No se puede establecer como puerta de una habitaci?n un valor menor que " + MIN_NUMERO_PUERTA + " ni mayor que " + MAX_NUMERO_PUERTA + ".");
        }
        this.puerta = puerta;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {

        if(precio < MIN_PRECIO_HABITACION || precio > MAX_PRECIO_HABITACION) {
            throw new IllegalArgumentException("ERROR: No se puede establecer como precio de una habitaci?n un valor menor que " + MIN_PRECIO_HABITACION + " ni mayor que " + MAX_PRECIO_HABITACION + ".");
        }


        this.precio = precio;
    }
    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        if(tipoHabitacion == null) {
            throw new NullPointerException("ERROR: No se puede establecer un tipo de habitaci?n nula.");
        }
        this.tipoHabitacion = tipoHabitacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Habitacion that = (Habitacion) o;
        return Objects.equals(identificador, that.identificador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificador);
    }

    @Override
    public String toString() {
        return String.format("identificador=%s (%d-%d), precio habitaci?n=%s, tipo habitaci?n=%s",identificador,planta,puerta,precio,tipoHabitacion);
    }

}
