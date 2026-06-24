package com.itsqmet.ejemplotaller2.service;

import com.itsqmet.ejemplotaller2.model.auto;
import com.itsqmet.ejemplotaller2.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutoService {

    @Autowired
    private AutoRepository autoRepository;

    public List<auto> obtenerTodo() {
        return autoRepository.findAll();
    }

    public Optional<auto> buscarPorId(Long id) {
        return autoRepository.findById(id);
    }

    public auto crearAuto(auto auto) {
        return autoRepository.save(auto);
    }

    public boolean eliminar(Long id) {
        if (autoRepository.existsById(id)) {
            autoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<auto> actualizar(Long id, auto autoActualizado) {
        return autoRepository.findById(id).map(a -> {

            a.setMarca(autoActualizado.getMarca());
            a.setModelo(autoActualizado.getModelo());
            a.setAnio(autoActualizado.getAnio());
            a.setColor(autoActualizado.getColor());
            a.setPrecio(autoActualizado.getPrecio());
            a.setNumeroChasis(autoActualizado.getNumeroChasis());
            a.setEstado(autoActualizado.getEstado());

            return autoRepository.save(a);
        });
    }
}