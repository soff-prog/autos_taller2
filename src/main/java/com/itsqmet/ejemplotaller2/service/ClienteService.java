package com.itsqmet.ejemplotaller2.service;

import com.itsqmet.ejemplotaller2.model.Cliente;
import com.itsqmet.ejemplotaller2.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> obtenerTodo() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public boolean eliminar(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Cliente> actualizar(Long id, Cliente clienteActualizado) {

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