package com.hampcode.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.hampcode.model.entity.Cliente;
import com.hampcode.model.repository.ClienteRepository;

@Named
public class ClienteBusiness implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteRepository clienteRepository;

	@Transactional
	public Long insert(Cliente cliente) throws Exception {
		return clienteRepository.insert(cliente);
	}

	@Transactional
	public Long update(Cliente cliente) throws Exception {
		return clienteRepository.update(cliente);
	}
	

	public List<Cliente> getAll() throws Exception {
		return clienteRepository.findAll();
	}

	public List<Cliente> getClientePorNombre(String nombre) throws Exception {
		return clienteRepository.findByName(nombre);
	}

}
