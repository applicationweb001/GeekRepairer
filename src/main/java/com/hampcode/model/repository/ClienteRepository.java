package com.hampcode.model.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.hampcode.model.entity.Cliente;


@Named
public class ClienteRepository implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName="pwPU")
	private EntityManager em;
	
	
	public Long insert(Cliente cliente) throws Exception {
		em.persist(cliente);
		return cliente.getId();
	}
	
	public Long update(Cliente cliente) throws Exception{
		em.merge(cliente);
		return cliente.getId();
	}
	
	public void delete(Cliente cliente)throws Exception{
		em.remove(cliente);
	}
	
	public List<Cliente> findAll() throws Exception{
		List<Cliente> cliente=new ArrayList<>();
		
		TypedQuery<Cliente> query=em.createQuery("FROM Cliente",
				Cliente.class);
		
		cliente=query.getResultList();
		
		return cliente;
	}
	
	public Optional<Cliente> findById(Long id) throws Exception{
		Cliente clientFound;
		
		TypedQuery<Cliente> query=em.createQuery("FROM Cliente p WHERE p.id=?1"
				,Cliente.class);
		
		
		query.setParameter(1, id);
		clientFound=query.getSingleResult();
		
		return Optional.of(clientFound);
	}
	
	
	public List<Cliente> findByName(String nombre) throws Exception{
		List<Cliente> clientes=new ArrayList<>();
		
		TypedQuery<Cliente> query=em.createQuery("FROM Cliente p WHERE p.nombre LIKE ?1"
				,Cliente.class);
		query.setParameter(1, "%"+nombre+"%");
		clientes=query.getResultList();
		
		return clientes;
	}
	
	

}
