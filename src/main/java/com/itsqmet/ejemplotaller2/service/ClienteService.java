package com.itsqmet.ejemplotaller2.service;

import com.itsqmet.ejemplotaller2.model.cliente;
import com.itsqmet.ejemplotaller2.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<cliente> obtenerTodo() {
        return clienteRepository.findAll();
    }

    public Optional<cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public cliente crearCliente(cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public boolean eliminar(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<cliente> actualizar(Long id, cliente clienteActualizado) {

        return clienteRepository.findById(id).map(c -> {

            c.setCedula(clienteActualizado.getCedula());
            c.setNombre(clienteActualizado.getNombre());
            c.setApellido(clienteActualizado.getApellido());
            c.setTelefono(clienteActualizado.getTelefono());
            c.setCorreo(clienteActualizado.getCorreo());
            c.setDireccion(clienteActualizado.getDireccion());

            return clienteRepository.save(c);
        });
    }
}