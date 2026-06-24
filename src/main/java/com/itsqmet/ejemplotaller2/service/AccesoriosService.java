package com.itsqmet.ejemplotaller2.service;

import com.itsqmet.ejemplotaller2.model.Accesorios;
import com.itsqmet.ejemplotaller2.repository.AccesoriosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccesoriosService {

    @Autowired
    private AccesoriosRepository accesoriosRepository;

    public List<Accesorios> obtenerTodo() {
        return accesoriosRepository.findAll();
    }

    public Optional<Accesorios> buscarPorId(Long id) {
        return accesoriosRepository.findById(id);
    }

    public Accesorios crearAccesorio(Accesorios accesorio) {
        return accesoriosRepository.save(accesorio);
    }

    public boolean eliminar(Long id) {
        if (accesoriosRepository.existsById(id)) {
            accesoriosRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Accesorios> actualizar(Long id, Accesorios accesorioActualizado) {
        return accesoriosRepository.findById(id).map(accesorio -> {
            accesorio.setNombre(accesorioActualizado.getNombre());
            accesorio.setDescripcion(accesorioActualizado.getDescripcion());
            accesorio.setMarca(accesorioActualizado.getMarca());
            accesorio.setPrecio(accesorioActualizado.getPrecio());
            accesorio.setStock(accesorioActualizado.getStock());
            accesorio.setGarantia(accesorioActualizado.getGarantia());

            return accesoriosRepository.save(accesorio);
        });
    }
}