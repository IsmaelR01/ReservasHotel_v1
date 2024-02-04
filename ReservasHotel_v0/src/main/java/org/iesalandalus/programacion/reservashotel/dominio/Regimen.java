package org.iesalandalus.programacion.reservashotel.dominio;

public enum Regimen {
    SOLO_ALOJAMIENTO("Solo Alojamiento",0),
    ALOJAMIENTO_DESAYUNO("Alojamiento con Desayuno",15),
    MEDIA_PENSION("Media Pensión",30),
    PENSION_COMPLETA("Pensión Completa",50);

    private String cadenaAMostrar;
    private double incrementoPrecio;

    private Regimen(String cadenaAMostrar, double incrementoPrecio) {
        this.cadenaAMostrar = cadenaAMostrar;
        this.incrementoPrecio = incrementoPrecio;
    }

    public double getIncrementoPrecio() {
        return incrementoPrecio;
    }

    @Override
    public String toString() {

        return (ordinal() + 1) + ".- " +  cadenaAMostrar;
    }
}
