package com.evaluacion.tareaevaluada3_cristianjimenez.Controlador;

import com.evaluacion.tareaevaluada3_cristianjimenez.Dto.CrearMuebleRequest;
import com.evaluacion.tareaevaluada3_cristianjimenez.Dto.ItemVentaRequest;
import com.evaluacion.tareaevaluada3_cristianjimenez.Dto.MuebleDto;
import com.evaluacion.tareaevaluada3_cristianjimenez.Dto.VentaResponse;
import com.evaluacion.tareaevaluada3_cristianjimenez.Modelo.Variante;
import com.evaluacion.tareaevaluada3_cristianjimenez.Servicio.MuebleService;
import com.evaluacion.tareaevaluada3_cristianjimenez.Servicio.VentaService;
import com.evaluacion.tareaevaluada3_cristianjimenez.Servicio.VarianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private MuebleService muebleService;

    @Autowired
    private VentaService ventaService;

    @Autowired
    private VarianteService varianteService;

    @PostMapping("/muebles")
    public ResponseEntity<MuebleDto> crearMueble(@RequestBody CrearMuebleRequest Dto) {
        MuebleDto muebleCreado = muebleService.crearMueble(Dto);
        return new ResponseEntity<>(muebleCreado, HttpStatus.CREATED);
    }

    @GetMapping("/muebles")
    public ResponseEntity<List<MuebleDto>> listarMuebles() {
        return ResponseEntity.ok(muebleService.listarMuebles());
    }

    @GetMapping("/muebles/{id}")
    public ResponseEntity<MuebleDto> obtenerMueblePorId(@PathVariable Long id) {
        return muebleService.obtenerMueblePorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/muebles/{id}")
    public ResponseEntity<MuebleDto> actualizarMueble(@PathVariable Long id, @RequestBody CrearMuebleRequest Dto) {
        try {
            MuebleDto muebleActualizado = muebleService.actualizarMueble(id, Dto);
            return ResponseEntity.ok(muebleActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/muebles/{id}/desactivar")
    public ResponseEntity<MuebleDto> desactivarMueble(@PathVariable Long id) {
        try {
            MuebleDto muebleDesactivado = muebleService.desactivarMueble(id);
            return ResponseEntity.ok(muebleDesactivado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/muebles/{id}/activar")
    public ResponseEntity<MuebleDto> activarMueble(@PathVariable Long id) {
        try {
            MuebleDto muebleActivado = muebleService.activarMueble(id);
            return ResponseEntity.ok(muebleActivado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/variantes")
    public ResponseEntity<Variante> crearVariante(@RequestBody Variante variante) {
        Variante nuevaVariante = varianteService.crearVariante(variante);
        return new ResponseEntity<>(nuevaVariante, HttpStatus.CREATED);
    }

    @GetMapping("/variantes")
    public ResponseEntity<List<Variante>> listarVariantes() {
        return ResponseEntity.ok(varianteService.listarVariantes());
    }

    @PostMapping("/cotizar")
    public ResponseEntity<Double> cotizar(@RequestBody List<ItemVentaRequest> items) {
        double total = ventaService.calcularCotizacion(items);
        return ResponseEntity.ok(total);
    }

    @PostMapping("/ventas")
    public ResponseEntity<VentaResponse> vender(@RequestBody List<ItemVentaRequest> items) {
        try {
            VentaResponse respuesta = ventaService.confirmarVenta(items);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new VentaResponse(e.getMessage(), 0.0));
        }
    }
}