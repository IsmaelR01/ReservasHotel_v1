package org.iesalandalus.programacion.reservashotel.modelo.dominio;

import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Huesped {
    private static final String ER_TELEFONO = ("^[679]\\d{8}$");
    private static final String ER_CORREO = ("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9]+)+\\.[a-zA-Z]{2,7}$") ;
    private static final String ER_DNI = "([0-9]{8})([A-Z])";
    public static final String  FORMATO_FECHA = "dd/MM/yyyy";
    private String nombre;
    private String telefono;
    private String correo;
    private String dni;
    private LocalDate fechaNacimiento;

    public Huesped(String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento) {
        setNombre(nombre);
        setDni(dni);
        setCorreo(correo);
        setTelefono(telefono);
        setFechaNacimiento(fechaNacimiento);
    }
    public Huesped(Huesped huesped) {
        if(huesped == null) {
            throw new NullPointerException("ERROR: No es posible copiar un hu�sped nulo.");
        }
        setNombre(huesped.getNombre());
        setDni(huesped.getDni());
        setCorreo(huesped.getCorreo());
        setTelefono(huesped.getTelefono());
        setFechaNacimiento(huesped.getFechaNacimiento());
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {

        if(nombre == null) {
            throw new NullPointerException("ERROR: El nombre de un hu�sped no puede ser nulo.");
        }
        if(nombre.isBlank()) {
            throw new IllegalArgumentException("ERROR: El nombre de un hu�sped no puede estar vac�o.");
        }

        this.nombre = formateaNombre(nombre);
    }
    private String formateaNombre(String nombre) {

        String[] palabras = nombre.trim().split("\\s+");
        for (int i = 0; i < palabras.length; i++) {
            palabras[i] = palabras[i].substring(0, 1).toUpperCase() + palabras[i].substring(1).toLowerCase();
        }
        return String.join(" ", palabras);
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {

        if(telefono == null) {
            throw new NullPointerException("ERROR: El tel�fono de un hu�sped no puede ser nulo.");
        }

        if(!telefono.matches(ER_TELEFONO)) {
            throw new IllegalArgumentException("ERROR: El tel�fono del hu�sped no tiene un formato v�lido.");
        }


        this.telefono = telefono;
    }
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {

        if (correo == null) {
            throw new NullPointerException("ERROR: El correo de un hu�sped no puede ser nulo.");
        }

        if(!correo.matches(ER_CORREO)) {
            throw new IllegalArgumentException("ERROR: El correo del hu�sped no tiene un formato v�lido.");
        }

        this.correo = correo;
    }
    public String getDni() {
        return dni;
    }
    private void setDni(String dni) {
        if(dni == null) {
            throw new NullPointerException("ERROR: El dni de un hu�sped no puede ser nulo.");
        }
        if(!dni.matches(ER_DNI)) {
            throw new IllegalArgumentException("ERROR: El dni del hu�sped no tiene un formato v�lido.");
        }

        if(!comprobarLetraDni(dni)) {
            throw new IllegalArgumentException("ERROR: La letra del dni del hu�sped no es correcta.");
        }
        this.dni = dni;
    }

    private Boolean comprobarLetraDni(String dni) {
        Pattern pattern = Pattern.compile(ER_DNI);
        Matcher matcher = pattern.matcher(dni);
        if (matcher.matches()) {
            String numerosDni = matcher.group(1);
            String letraDni = matcher.group(2);
            int resto = Integer.parseInt(numerosDni) % 23;
            char letraCalculada = "TRWAGMYFPDXBNJZSQVHLCKE".charAt(resto);
            return letraCalculada == letraDni.charAt(0);
        } else {
            return false;

        }
    }
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    private void setFechaNacimiento(LocalDate fechaNacimiento) {
        if(fechaNacimiento == null) {
            throw new NullPointerException("ERROR: La fecha de nacimiento de un hu�sped no puede ser nula.");
        }
        if(fechaNacimiento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("ERROR: La fecha de nacimiento de un hu�sped no puede ser posterior a hoy.");
        }
        this.fechaNacimiento = fechaNacimiento;
    }
    private String getIniciales() {
        StringBuilder letrasIniciales = new StringBuilder();
        String[] palabras = this.nombre.split("\\s");

        for (int i = 0; i < palabras.length; i++) {
            String palabra = palabras[i];
            if (!palabra.isEmpty()) {
                letrasIniciales.append(palabra.substring(0, 1).toUpperCase());
            }
        }

        return letrasIniciales.toString();
    }
       /*
        char nombre = iniciales.nextToken().toUpperCase().charAt(0);
        char segundoNombre = iniciales.nextToken().toUpperCase().charAt(0);
        char apellidos1 = iniciales.nextToken().toUpperCase().charAt(0);
        char apellidos2 = iniciales.nextToken().toUpperCase().charAt(0);
        return "" + nombre + segundoNombre + apellidos1 + apellidos2;

        */


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Huesped huesped = (Huesped) o;
        return Objects.equals(dni, huesped.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return String.format("nombre=%s (%s), DNI=%s, correo=%s, tel�fono=%s, fecha nacimiento=%s",nombre,getIniciales(),dni,correo,telefono,fechaNacimiento.format(DateTimeFormatter.ofPattern(FORMATO_FECHA)));
    }

}
