package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public enum Regimen {
    SOLO_ALOJAMIENTO("Solo Alojamiento",0),
    ALOJAMIENTO_DESAYUNO("Alojamiento con Desayuno",15),
    MEDIA_PENSION("Media Pensi�n",30),
    PENSION_COMPLETA("Pensi�n Completa",50);

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
