package com.evaluacion.tareaevaluada3_cristianjimenez.Strategy;
import com.evaluacion.tareaevaluada3_cristianjimenez.Modelo.Variante.TipoVariante;

public class CalculoPrecioFactory {

    public static CalculoPrecioStrategy getStrategy(TipoVariante tipo) {
        switch (tipo) {
            case SUMA_FIJA:
                return new SumaFijaStrategy();
            case PORCENTAJE:
                return new PorcentajeStrategy();
            default:
                throw new IllegalArgumentException("Tipo de variante no soportado: " + tipo);
        }
    }
}