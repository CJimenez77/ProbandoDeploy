package com.evaluacion.tareaevaluada3_cristianjimenez.Strategy;
import com.evaluacion.tareaevaluada3_cristianjimenez.Modelo.Variante;

public interface CalculoPrecioStrategy {
    double calcular(double precioActual, Variante variante);
}