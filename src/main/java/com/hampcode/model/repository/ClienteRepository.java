package com.hampcode.model.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.hampcode.model.entity.Client;

@Named
public class ClienteRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "pwPU")
	private EntityManager em;

	public Long insert(Client cliente) throws Exception {
		em.persist(cliente);
		return cliente.getIdCliente();
	}

	public Long update(Client cliente) throws Exception {
		em.merge(cliente);
		return cliente.getIdCliente();
	}

	public void delete(Client cliente) throws Exception {
		em.remove(cliente);
	}

	public List<Client> findAll() throws Exception {
		List<Client> cliente = new ArrayList<>();

		TypedQuery<Client> query = em.createQuery("FROM Client p WHERE p.id=?1", Client.class);

		cliente = query.getResultList();

		return cliente;
	}

	public Optional<Client> findById(Long id) throws Exception {
		Client clientFound;

		TypedQuery<Client> query = em.createQuery("FROM Client p WHERE p.id=?1", Client.class);

		query.setParameter(1, id);
		clientFound = query.getSingleResult();

		return Optional.of(clientFound);
	}

	public List<Client> findByName(String nombre) throws Exception {
		List<Client> clientes = new ArrayList<>();

		TypedQuery<Client> query = em.createQuery("FROM Client p WHERE p.nombre LIKE ?1", Client.class);
		query.setParameter(1, "%" + nombre + "%");
		clientes = query.getResultList();

		return clientes;
	}

}
