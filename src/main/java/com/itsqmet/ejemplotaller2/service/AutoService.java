package com.itsqmet.ejemplotaller2.service;

import com.itsqmet.ejemplotaller2.model.Auto;
import com.itsqmet.ejemplotaller2.model.Cliente;
import com.itsqmet.ejemplotaller2.repository.AutoRepository;
import com.itsqmet.ejemplotaller2.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutoService {

    @Autowired
    private AutoRepository autoRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    public List<Auto> obtenerTodo() {
        return autoRepository.findAll();
    }


    public Optional<Auto> buscarPorId(Long id) {
        return autoRepository.findById(id);
    }


    public Auto crearAuto(Auto auto) {

        if(auto.getCliente() != null){

            Long idCliente = auto.getCliente().getId();

            Cliente cliente = clienteRepository.findById(idCliente)
                    .orElseThrow(() ->
                            new RuntimeException("Cliente no existe"));

            auto.setCliente(cliente);
        }

        return autoRepository.save(auto);
    }


    public boolean eliminar(Long id){

        if(autoRepository.existsById(id)){
            autoRepository.deleteById(id);
            return true;
        }

        return false;
    }


    public Optional<Auto> actualizar(Long id, Auto autoActualizado){

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