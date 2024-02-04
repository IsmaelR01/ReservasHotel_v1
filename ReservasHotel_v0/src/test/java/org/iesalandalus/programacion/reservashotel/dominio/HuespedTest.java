package org.iesalandalus.programacion.reservashotel.modelo.dominio;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class HuespedTest {

    private static final String CADENA_NO_ESPERADA = "La cadena devuelta no es la esperada.";

    private static final String NOMBRE_NO_ESPERADO = "El nombre devuelto no es el mismo que el pasado al constructor.";
    private static final String DNI_NO_ESPERADO = "El DNI devuelto no es el mismo que el pasado al constructor.";
    private static final String TELEFONO_NO_ESPERADO = "El tel�fono devuelto no es el mismo que el pasado al constructor.";
    private static final String CORREO_NO_ESPERADO = "El correo devuelto no es el mismo que el pasado al constructor.";
    private static final String FECHA_NACIMIENTO_NO_ESPERADA = "La fecha de nacimiento devuelta no es la misma que la pasada al constructor.";
    private static final String NOMBRE_JRJR = "Jos� Ram�n Jim�nez Reyes";
    private static final String DNI_JRJR = "11223344B";
    private static final String TELEFONO_JRJR = "950112233";
    private static final String CORREO_JRJR = "joseramon.jimenez@iesalandalus.org";
    private static final String NOMBRE_MAL_ARDR = "  ANDR�S   RuBiO   dEl             r�o";
    private static final String NOMBRE_ARDR = "Andr�s Rubio Del R�o";
    private static final String DNI_ARDR = "22334455Y";
    private static final String TELEFONO_ARDR = "666223344";
    private static final String CORREO_ARDR = "andres.rubio@iesalandalus.org";

    private static final LocalDate FECHA_NACIMIENTO_JRJR=LocalDate.of(2002, 9, 15);
    private static final LocalDate FECHA_NACIMIENTO_ARDR=LocalDate.of(1992, 7, 3);
    private static final String FORMATO_FECHA="dd/MM/yyyy";
    private static final String TIPO_EXCEPCION_NO_CORRECTA = "El tipo de la excepci�n no es correcto.";
    private static final String NOMBRE_INCORRECTO = "Deber�a haber saltado una excepci�n indicando que el nombre es incorrecto";
    private static final String ERROR_NOMBRE_NULO = "ERROR: El nombre de un hu�sped no puede ser nulo.";
    private static final String ERROR_NOMBRE_NO_VALIDO = "ERROR: El nombre de un hu�sped no puede estar vac�o.";
    private static final String DNI_INCORRECTO = "Deber�a haber saltado una excepci�n indicando que el DNI es incorrecto";
    private static final String ERROR_DNI_NULO = "ERROR: El dni de un hu�sped no puede ser nulo.";
    private static final String ERROR_DNI_NO_VALIDO = "ERROR: El dni del hu�sped no tiene un formato v�lido.";
    private static final String ERROR_CORREO_NULO = "ERROR: El correo de un hu�sped no puede ser nulo.";
    private static final String CORREO_INCORRECTO = "Deber�a haber saltado una excepci�n indicando que el correo es incorrecto";
    private static final String ERROR_CORREO_NO_VALIDO = "ERROR: El correo del hu�sped no tiene un formato v�lido.";
    private static final String ERROR_TELEFONO_NO_VALIDO = "ERROR: El tel�fono del hu�sped no tiene un formato v�lido.";

    private static final String MENSAJE_EXCEPCION_NO_CORRECTO = "El mensaje devuelto por la excepci�n no es correcto.";
    private static final String OBJETO_DEBERIA_SER_NULO = "No se deber�a haber creado el objeto hu�sped.";
    private static final String ERROR_LETRA_DNI_NO_VALIDA = "ERROR: La letra del dni del hu�sped no es correcta.";
    private static final String TELEFONO_INCORRECTO = "Deber�a haber saltado una excepci�n indicando que el tel�fono es incorrecto";

    private static final String ERROR_TELEFONO_NULO = "ERROR: El tel�fono de un hu�sped no puede ser nulo.";
    private static final String FECHA_NACIMIENTO_INCORRECTA = "Deber�a haber saltado una excepci�n indicando que la fecha de nacimiento es incorrecta";
    private static final String ERROR_FECHA_NULA = "ERROR: La fecha de nacimiento de un hu�sped no puede ser nula.";
    private static final String HUESPED_NO_ESPERADO = "El hu�sped copiado deber�a ser el mismo que el pasado como par�metro.";
    private static final String HUESPED_NULO = "Deber�a haber saltado una excepci�n indicando que no se puede copiar un hu�sped nulo.";
    private static final String ERROR_COPIAR_HUESPED_NULO = "ERROR: No es posible copiar un hu�sped nulo.";

    @Test
    public void constructorNombreValidoDniValidoCorreoValidoTelefonoValidoFechaNacimientoValidaCreaHuespedCorrectamente() {
        Huesped huesped = new Huesped(NOMBRE_JRJR, DNI_JRJR, CORREO_JRJR, TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
        assertEquals(NOMBRE_JRJR, huesped.getNombre(), NOMBRE_NO_ESPERADO);
        assertEquals(DNI_JRJR, huesped.getDni(), DNI_NO_ESPERADO);
        assertEquals(CORREO_JRJR, huesped.getCorreo(), CORREO_NO_ESPERADO);
        assertEquals(TELEFONO_JRJR, huesped.getTelefono(), TELEFONO_NO_ESPERADO);
        assertEquals(FECHA_NACIMIENTO_JRJR, huesped.getFechaNacimiento(), FECHA_NACIMIENTO_NO_ESPERADA);

        huesped = new Huesped(NOMBRE_MAL_ARDR, DNI_ARDR, CORREO_ARDR, TELEFONO_ARDR, FECHA_NACIMIENTO_ARDR);
        assertEquals(NOMBRE_ARDR, huesped.getNombre(), NOMBRE_NO_ESPERADO);
        assertEquals(DNI_ARDR, huesped.getDni(), DNI_NO_ESPERADO);
        assertEquals(CORREO_ARDR, huesped.getCorreo(), CORREO_NO_ESPERADO);
        assertEquals(TELEFONO_ARDR, huesped.getTelefono(), TELEFONO_NO_ESPERADO);
        assertEquals(FECHA_NACIMIENTO_ARDR, huesped.getFechaNacimiento(), FECHA_NACIMIENTO_NO_ESPERADA);
    }

    @Test
    public void constructorNombreNoValidoDniValidoCorreoValidoTelefonoValidoFechaNacimientoValidaLanzaExcepcion() {
        Huesped huesped = null;
        try {
            huesped = new Huesped(null, DNI_JRJR, CORREO_JRJR, TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
            fail(NOMBRE_INCORRECTO);
        } catch (NullPointerException e) {
            assertEquals(ERROR_NOMBRE_NULO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertEquals(null, huesped, OBJETO_DEBERIA_SER_NULO);
        } catch (Exception e) {
            fail(TIPO_EXCEPCION_NO_CORRECTA);
        }
        try {
            huesped = new Huesped("", DNI_JRJR, CORREO_JRJR, TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
            fail(NOMBRE_INCORRECTO);
        } catch (IllegalArgumentException e) {
            assertEquals(ERROR_NOMBRE_NO_VALIDO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertEquals(null, huesped, OBJETO_DEBERIA_SER_NULO);
        } catch (Exception e) {
            fail(TIPO_EXCEPCION_NO_CORRECTA);
        }
        try {
            huesped = new Huesped("  ", DNI_JRJR, CORREO_JRJR, TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
            fail(NOMBRE_INCORRECTO);
        } catch (IllegalArgumentException e) {
            assertEquals(ERROR_NOMBRE_NO_VALIDO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertEquals(null, huesped, OBJETO_DEBERIA_SER_NULO);
        } catch (Exception e) {
            fail(TIPO_EXCEPCION_NO_CORRECTA);
        }
    }

    @Test
    public void constructorNombreValidoDniNoValidoCorreoValidoTelefonoValidoFechaNacimientoValidaLanzaExcepcion() {
        Huesped huesped = null;

        try {
            huesped = new Huesped(NOMBRE_JRJR, null, CORREO_JRJR, TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
            fail(DNI_INCORRECTO);
        } catch (NullPointerException e) {
            assertEquals(ERROR_DNI_NULO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertEquals(null, huesped, OBJETO_DEBERIA_SER_NULO);
        } catch (Exception e) {
            fail(TIPO_EXCEPCION_NO_CORRECTA);
        }

        try {
            huesped = new Huesped(NOMBRE_JRJR, "", CORREO_JRJR, TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
            fail(DNI_INCORRECTO);
        } catch (IllegalArgumentException e) {
            assertEquals(ERROR_DNI_NO_VALIDO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertEquals(null, huesped, OBJETO_DEBERIA_SER_NULO);
        } catch (Exception e) {
            fail(TIPO_EXCEPCION_NO_CORRECTA);
        }

        try {
            huesped = new Huesped(NOMBRE_JRJR, "   ", CORREO_JRJR, TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
            fail(DNI_INCORRECTO);
        } catch (IllegalArgumentException e) {
            assertEquals(ERROR_DNI_NO_VALIDO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertEquals(null, huesped, OBJETO_DEBERIA_SER_NULO);
        } catch (Exception e) {
            fail(TIPO_EXCEPCION_NO_CORRECTA);
        }

        try {
            huesped = new Huesped(NOMBRE_JRJR, "11223344", CORREO_JRJR, TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
            fail(DNI_INCORRECTO);
        } catch (IllegalArgumentException e) {
            assertEquals(ERROR_DNI_NO_VALIDO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertEquals(null, huesped, OBJETO_DEBERIA_SER_NULO);
        } catch (Exception e) {
            fail(TIPO_EXCEPCION_NO_CORRECTA);
        }

        try {
            huesped = new Huesped(NOMBRE_JRJR, "112233445A", CORREO_JRJR, TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
            fail(DNI_INCORRECTO);
        } catch (IllegalArgumentException e) {
            assertEquals(ERROR_DNI_NO_VALIDO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertEquals(null, huesped, OBJETO_DEBERIA_SER_NULO);
        } catch (Exception e) {
            fail(TIPO_EXCEPCION_NO_CORRECTA);
        }

        try {
            huesped = new Huesped(NOMBRE_JRJR, "11223344AA", CORREO_JRJR, TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
            fail(DNI_INCORRECTO);
        } catch (IllegalArgumentException e) {
            assertEquals(ERROR_DNI_NO_VALIDO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertEquals(null, huesped, OBJETO_DEBERIA_SER_NULO);
        } catch (Exception e) {
            fail(TIPO_EXCEPCION_NO_CORRECTA);
        }

        try {
            huesped = new Huesped(NOMBRE_JRJR, "11223344A", CORREO_JRJR, TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
            fail(DNI_INCORRECTO);
        } catch (IllegalArgumentException e) {
            assertEquals(ERROR_LETRA_DNI_NO_VALIDA, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertNull(huesped,OBJETO_DEBERIA_SER_NULO);
        } catch (Exception e) {
            fail(TIPO_EXCEPCION_NO_CORRECTA);
        }
    }

    @Test
    public void constructorNombreValidoDniValidoCorreoNoValidoTelefonoValidoFechaNacimientoValidaLanzaExcepcion() {
        Huesped huesped = null;

        try {
            huesped = new Huesped(NOMBRE_JRJR, DNI_JRJR, null, TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
            fail(CORREO_INCORRECTO);
        } catch (NullPointerException e) {
            assertEquals(ERROR_CORREO_NULO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertEquals(null, huesped, OBJETO_DEBERIA_SER_NULO);
        } catch (Exception e) {
            fail(TIPO_EXCEPCION_NO_CORRECTA);
        }

        try {
            huesped = new Huesped(NOMBRE_JRJR, DNI_JRJR, "", TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
            fail(CORREO_INCORRECTO);
        } catch (IllegalArgumentException e) {
            assertEquals(ERROR_CORREO_NO_VALIDO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertEquals(null, huesped, OBJETO_DEBERIA_SER_NULO);
        } catch (Exception e) {
            fail(TIPO_EXCEPCION_NO_CORRECTA);
        }

        try {
            huesped = new Huesped(NOMBRE_JRJR, DNI_JRJR, "   ", TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
            fail(CORREO_INCORRECTO);
        } catch (IllegalArgumentException e) {
            assertEquals(ERROR_CORREO_NO_VALIDO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertEquals(null, huesped, OBJETO_DEBERIA_SER_NULO);
        } catch (Exception e) {
            fail(TIPO_EXCEPCION_NO_CORRECTA);
        }

        try {
            huesped = new Huesped(NOMBRE_JRJR, DNI_JRJR, "11223344", TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
            fail(CORREO_INCORRECTO);
        } catch (IllegalArgumentException e) {
            assertEquals(ERROR_CORREO_NO_VALIDO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertEquals(null, huesped, OBJETO_DEBERIA_SER_NULO);
        } catch (Exception e) {
            fail(TIPO_EXCEPCION_NO_CORRECTA);
        }

        try {
            huesped = new Huesped(NOMBRE_JRJR, DNI_JRJR, "arr@.com", TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
            fail(CORREO_INCORRECTO);
        } catch (IllegalArgumentException e) {
            assertEquals(ERROR_CORREO_NO_VALIDO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertEquals(null, huesped, OBJETO_DEBERIA_SER_NULO);
        } catch (Exception e) {
            fail(TIPO_EXCEPCION_NO_CORRECTA);
        }

        try {
            huesped = new Huesped(NOMBRE_JRJR, DNI_JRJR, "arr.es.com", TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
            fail(CORREO_INCORRECTO);
        } catch (IllegalArgumentException e) {
            assertEquals(ERROR_CORREO_NO_VALIDO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertEquals(null, huesped, OBJETO_DEBERIA_SER_NULO);
        } catch (Exception e) {
            fail(TIPO_EXCEPCION_NO_CORRECTA);
        }

        try {
            huesped = new Huesped(NOMBRE_JRJR, DNI_JRJR, "arr@gmail", TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
            fail(CORREO_INCORRECTO);
        } catch (IllegalArgumentException e) {
            assertEquals(ERROR_CORREO_NO_VALIDO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertNull(huesped,OBJETO_DEBERIA_SER_NULO);
        } catch (Exception e) {
            fail(TIPO_EXCEPCION_NO_CORRECTA);
        }
    }

    @Test
    public void constructorNombreValidoDniValidoCorreoValidoTelefonoNoValidoFechaNacimientoValidaLanzaExcepcion() {
        Huesped huesped = null;

        try {
            huesped = new Huesped(NOMBRE_JRJR, DNI_JRJR, CORREO_JRJR, null, FECHA_NACIMIENTO_JRJR);
            fail(TELEFONO_INCORRECTO);
        } catch (NullPointerException e) {
            assertEquals(ERROR_TELEFONO_NULO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertEquals(null, huesped, OBJETO_DEBERIA_SER_NULO);
        } catch (Exception e) {
            fail(TIPO_EXCEPCION_NO_CORRECTA);
        }

        try {
            huesped = new Huesped(NOMBRE_JRJR, DNI_JRJR, CORREO_JRJR, "", FECHA_NACIMIENTO_JRJR);
            fail(TELEFONO_INCORRECTO);
        } catch (IllegalArgumentException e) {
            assertEquals(ERROR_TELEFONO_NO_VALIDO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertEquals(null, huesped, OBJETO_DEBERIA_SER_NULO);
        } catch (Exception e) {
            fail(TIPO_EXCEPCION_NO_CORRECTA);
        }

        try {
            huesped = new Huesped(NOMBRE_JRJR, DNI_JRJR, CORREO_JRJR, "   ", FECHA_NACIMIENTO_JRJR);
            fail(TELEFONO_INCORRECTO);
        } catch (IllegalArgumentException e) {
            assertEquals(ERROR_TELEFONO_NO_VALIDO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertEquals(null, huesped, OBJETO_DEBERIA_SER_NULO);
        } catch (Exception e) {
            fail(TIPO_EXCEPCION_NO_CORRECTA);
        }

        try {
            huesped = new Huesped(NOMBRE_JRJR, DNI_JRJR, CORREO_JRJR, "1", FECHA_NACIMIENTO_JRJR);
            fail(TELEFONO_INCORRECTO);
        } catch (IllegalArgumentException e) {
            assertEquals(ERROR_TELEFONO_NO_VALIDO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertEquals(null, huesped, OBJETO_DEBERIA_SER_NULO);
        } catch (Exception e) {
            fail(TIPO_EXCEPCION_NO_CORRECTA);
        }

        try {
            huesped = new Huesped(NOMBRE_JRJR, DNI_JRJR, CORREO_JRJR, "arr@.com", FECHA_NACIMIENTO_JRJR);
            fail(TELEFONO_INCORRECTO);
        } catch (IllegalArgumentException e) {
            assertEquals(ERROR_TELEFONO_NO_VALIDO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertEquals(null, huesped, OBJETO_DEBERIA_SER_NULO);
        } catch (Exception e) {
            fail(TIPO_EXCEPCION_NO_CORRECTA);
        }

        try {
            huesped = new Huesped(NOMBRE_JRJR, DNI_JRJR, CORREO_JRJR, "9991122334", FECHA_NACIMIENTO_JRJR);
            fail(TELEFONO_INCORRECTO);
        } catch (IllegalArgumentException e) {
            assertEquals(ERROR_TELEFONO_NO_VALIDO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertEquals(null, huesped, OBJETO_DEBERIA_SER_NULO);
        } catch (Exception e) {
            fail(TIPO_EXCEPCION_NO_CORRECTA);
        }

        try {
            huesped = new Huesped(NOMBRE_JRJR, DNI_JRJR, CORREO_JRJR, "99911223", FECHA_NACIMIENTO_JRJR);
            fail(TELEFONO_INCORRECTO);
        } catch (IllegalArgumentException e) {
            assertEquals(ERROR_TELEFONO_NO_VALIDO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertNull(huesped,OBJETO_DEBERIA_SER_NULO);
        } catch (Exception e) {
            fail(TIPO_EXCEPCION_NO_CORRECTA);
        }
    }

    @Test
    public void constructorNombreValidoDniValidoCorreoValidoTelefonoValidoFechaNacimientoNoValidaLanzaExcepcion() {
        Huesped huesped = null;

        try {
            huesped = new Huesped(NOMBRE_JRJR, DNI_JRJR, CORREO_JRJR, TELEFONO_JRJR, null);
            fail(FECHA_NACIMIENTO_INCORRECTA);
        } catch (NullPointerException e) {
            assertEquals(ERROR_FECHA_NULA, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertEquals(null, huesped, OBJETO_DEBERIA_SER_NULO);
        } catch (Exception e) {
            fail(TIPO_EXCEPCION_NO_CORRECTA);
        }
    }

    @Test
    public void constructorHuespedValidoCopiaHuespedCorrectamente() {
        Huesped huesped1 = new Huesped(NOMBRE_ARDR, DNI_ARDR, CORREO_ARDR, TELEFONO_ARDR, FECHA_NACIMIENTO_ARDR);
        Huesped huesped2 = new Huesped(huesped1);
        assertEquals(huesped1, huesped2, HUESPED_NO_ESPERADO);
        assertEquals(NOMBRE_ARDR, huesped2.getNombre(), NOMBRE_NO_ESPERADO);
        assertEquals(DNI_ARDR, huesped2.getDni(), DNI_NO_ESPERADO);
        assertEquals(CORREO_ARDR, huesped2.getCorreo(), CORREO_NO_ESPERADO);
        assertEquals(TELEFONO_ARDR, huesped2.getTelefono(), TELEFONO_NO_ESPERADO);
        assertEquals(FECHA_NACIMIENTO_ARDR, huesped2.getFechaNacimiento(), FECHA_NACIMIENTO_NO_ESPERADA);
    }

    @Test
    public void constructorHuespedNuloLanzaExcepcion() {
        Huesped huesped = null;

        try {
            huesped = new Huesped(null);
            fail(HUESPED_NULO);
        } catch (NullPointerException e) {
            assertEquals(ERROR_COPIAR_HUESPED_NULO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertEquals(null, huesped, OBJETO_DEBERIA_SER_NULO);
        } catch (Exception e) {
            fail(TIPO_EXCEPCION_NO_CORRECTA);
        }
    }

    @Test
    public void toStringDevuelveLaCadenaEsperada() {
        Huesped huesped = new Huesped(NOMBRE_JRJR, DNI_JRJR, CORREO_JRJR, TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);


        assertEquals(String.format("nombre=%s (%s), DNI=%s, correo=%s, tel�fono=%s, fecha nacimiento=%s", NOMBRE_JRJR, "JRJR", DNI_JRJR, CORREO_JRJR, TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR.format(DateTimeFormatter.ofPattern(FORMATO_FECHA))), huesped.toString(), CADENA_NO_ESPERADA);

        huesped = new Huesped(NOMBRE_MAL_ARDR, DNI_ARDR, CORREO_ARDR, TELEFONO_ARDR, FECHA_NACIMIENTO_ARDR);
        assertEquals(String.format("nombre=%s (%s), DNI=%s, correo=%s, tel�fono=%s, fecha nacimiento=%s", NOMBRE_ARDR, "ARDR", DNI_ARDR, CORREO_ARDR, TELEFONO_ARDR, FECHA_NACIMIENTO_ARDR.format(DateTimeFormatter.ofPattern(FORMATO_FECHA))), huesped.toString(), CADENA_NO_ESPERADA);
    }


}
