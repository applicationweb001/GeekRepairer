package com.hampcode.business;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.hampcode.model.entity.Client;
import com.hampcode.model.entity.Product;
import com.hampcode.model.repository.ClienteRepository;

@Named
public class ClienteBusiness {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ClienteRepository clienteRepository;
	
	@Transactional
	
	private Long insert(Client cliente)throws Exception{
		return clienteRepository.insert(cliente);
	}
	
	@Transactional
	public Long update(Client cliente) throws Exception{
		return clienteRepository.insert(cliente);
	}
	
	
	public List<Client> getAll() throws Exception {
		return clienteRepository.findAll();
	}
	
	
	public List<Client> getClientePorNombre(String nombre) throws Exception{
		return clienteRepository.findByName(nombre);
	}
	
	

}
