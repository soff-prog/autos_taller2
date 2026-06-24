package com.itsqmet.ejemplotaller2.service;

import com.itsqmet.ejemplotaller2.model.matricula;
import com.itsqmet.ejemplotaller2.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    public List<matricula> obtenerTodo() {
        return matriculaRepository.findAll();
    }

    public Optional<matricula> buscarPorId(Long id) {
        return matriculaRepository.findById(id);
    }

    public matricula crearMatricula(matricula matricula) {
        return matriculaRepository.save(matricula);
    }

    public boolean eliminar(Long id) {

        if (matriculaRepository.existsById(id)) {
            matriculaRepository.deleteById(id);
            return true;
        }

        return false;
    }

    public Optional<matricula> actualizar(Long id, matricula matriculaActualizada) {

        return matriculaRepository.findById(id).map(m -> {

            m.setNumeroMatricula(matriculaActualizada.getNumeroMatricula());
            m.setFechaEmision(matriculaActualizada.getFechaEmision());
            m.setFechaCaducidad(matriculaActualizada.getFechaCaducidad());
            m.setProvincia(matriculaActualizada.getProvincia());
            m.setEstado(matriculaActualizada.getEstado());

            return matriculaRepository.save(m);
        });
    }
}