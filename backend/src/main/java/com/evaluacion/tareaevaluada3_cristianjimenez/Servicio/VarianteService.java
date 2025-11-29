package com.evaluacion.tareaevaluada3_cristianjimenez.Servicio;

import com.evaluacion.tareaevaluada3_cristianjimenez.Modelo.Variante;
import com.evaluacion.tareaevaluada3_cristianjimenez.Repositorio.VarianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VarianteService {

    @Autowired
    private VarianteRepository varianteRepository;

    public Variante crearVariante(Variante variante) {
        return varianteRepository.save(variante);
    }

    public List<Variante> listarVariantes() {
        return varianteRepository.findAll();
    }
}