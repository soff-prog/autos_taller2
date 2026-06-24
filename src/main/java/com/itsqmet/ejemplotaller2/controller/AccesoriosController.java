package com.itsqmet.ejemplotaller2.controller;

import com.itsqmet.ejemplotaller2.model.accesorios;
import com.itsqmet.ejemplotaller2.service.AccesoriosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accesorios")
@CrossOrigin("*")
public class AccesoriosController {

    @Autowired
    private AccesoriosService accesoriosService;

    @GetMapping
    public ResponseEntity<List<accesorios>> obtenerTodos() {
        return ResponseEntity.ok(accesoriosService.obtenerTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return accesoriosService.buscarPorId(id)
                .map(accesorio -> ResponseEntity.ok((Object) accesorio))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Accesorio con id " + id + " no encontrado")));
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody accesorios accesorio,
                                   BindingResult result) {

        if (result.hasErrors()) {

            Map<String, String> errores = new HashMap<>();

            result.getFieldErrors().forEach(error ->
                    errores.put(error.getField(),
                            error.getDefaultMessage()));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }

        accesorios nuevo = accesoriosService.crearAccesorio(accesorio);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {

        if (accesoriosService.eliminar(id)) {
            return ResponseEntity.ok(
                    Map.of("mensaje", "Accesorio eliminado correctamente"));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error",
                        "Accesorio con id " + id + " no encontrado"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @Valid @RequestBody accesorios accesorio,
                                        BindingResult result) {

        if (result.hasErrors()) {

            Map<String, String> errores = new HashMap<>();

            result.getFieldErrors().forEach(error ->
                    errores.put(error.getField(),
                            error.getDefaultMessage()));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }

        return accesoriosService.actualizar(id, accesorio)
                .map(actualizado -> ResponseEntity.ok((Object) actualizado))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Accesorio con id " + id + " no encontrado")));
    }
}