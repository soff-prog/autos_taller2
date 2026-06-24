package com.itsqmet.ejemplotaller2.controller;

import com.itsqmet.ejemplotaller2.model.matricula;
import com.itsqmet.ejemplotaller2.service.MatriculaService;
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
@RequestMapping("/api/matriculas")
@CrossOrigin("*")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @GetMapping
    public ResponseEntity<List<matricula>> obtenerTodos() {
        return ResponseEntity.ok(matriculaService.obtenerTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {

        return matriculaService.buscarPorId(id)
                .map(matricula -> ResponseEntity.ok((Object) matricula))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Matrícula con id " + id + " no encontrada")));
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody matricula matricula,
                                   BindingResult result) {

        if (result.hasErrors()) {

            Map<String, String> errores = new HashMap<>();

            result.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage()));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }

        matricula nueva = matriculaService.crearMatricula(matricula);

        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {

        if (matriculaService.eliminar(id)) {

            return ResponseEntity.ok(
                    Map.of("mensaje",
                            "Matrícula eliminada correctamente"));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error",
                        "Matrícula con id " + id + " no encontrada"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @Valid @RequestBody matricula matricula,
                                        BindingResult result) {

        if (result.hasErrors()) {

            Map<String, String> errores = new HashMap<>();

            result.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage()));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }

        return matriculaService.actualizar(id, matricula)
                .map(actualizado -> ResponseEntity.ok((Object) actualizado))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Matrícula con id " + id + " no encontrada")));
    }
}