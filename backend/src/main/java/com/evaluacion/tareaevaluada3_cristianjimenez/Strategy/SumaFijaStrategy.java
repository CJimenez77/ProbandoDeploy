package com.evaluacion.tareaevaluada3_cristianjimenez.Strategy;
import com.evaluacion.tareaevaluada3_cristianjimenez.Modelo.Variante;

public class SumaFijaStrategy implements CalculoPrecioStrategy {
    @Override
    public double calcular(double precioActual, Variante variante) {
        return precioActual + variante.getValor();
    }
}