package com.hampcode.model.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.hampcode.model.entity.Ticket;

@Named
public class TicketRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "pwPU")
	private EntityManager em;

	public Long insert(Ticket ticket) throws Exception {
		em.persist(ticket);
		return ticket.getIdTicket();
	}

	public Long update(Ticket ticket) throws Exception {
		em.merge(ticket);
		return ticket.getIdTicket();
	}

	public void delete(Ticket ticket) throws Exception {
		em.remove(ticket);
	}

	public List<Ticket> findAll() throws Exception {
		List<Ticket> tickets = new ArrayList<>();

		TypedQuery<Ticket> query = em.createQuery("FROM Ticket tic", Ticket.class);
		tickets = query.getResultList();

		return tickets;
	}
	
	public List<Ticket> findAllCola() throws Exception {
		List<Ticket> tickets = new ArrayList<>();

		TypedQuery<Ticket> query = em.createQuery("FROM Ticket tic WHERE tic.estado = 'Cola' ", Ticket.class);
		tickets = query.getResultList();

		return tickets;
	}

	public Optional<Ticket> findById(Long id) throws Exception {
		Ticket ticketFound;

		TypedQuery<Ticket> query = em.createQuery("FROM Ticket tic WHERE tic.id=?1", Ticket.class);

		query.setParameter(1, id);
		ticketFound = query.getSingleResult();

		return Optional.of(ticketFound);
	}

	public Ticket findByIdTicket(String id) throws Exception {
		Ticket ticket = new Ticket();
		TypedQuery<Ticket> query = em.createQuery("FROM Ticket tic WHERE tic.id=?1", Ticket.class);
		query.setParameter(1, "%" + id + "%");
		ticket = query.getSingleResult();
		return ticket;
	}

}
