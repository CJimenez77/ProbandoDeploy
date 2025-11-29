package com.evaluacion.tareaevaluada3_cristianjimenez.Modelo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "variantes")
public class Variante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private TipoVariante tipo;

    private Double valor;


    public enum TipoVariante {
        SUMA_FIJA,
        PORCENTAJE
    }
}