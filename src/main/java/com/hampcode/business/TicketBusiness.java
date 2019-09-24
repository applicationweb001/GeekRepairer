package com.hampcode.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import com.hampcode.model.repository.TicketRepository;

import com.hampcode.model.entity.Ticket;

@Named
public class TicketBusiness implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private TicketRepository ticketRepository;

	@Transactional
	public Long insert(Ticket ticket) throws Exception {
		return ticketRepository.insert(ticket);
	}

	@Transactional
	public Long update(Ticket ticket) throws Exception {
		return ticketRepository.update(ticket);
	}
	

	public List<Ticket> getAll() throws Exception {
		return ticketRepository.findAll();
	}

	public Ticket getTicketID(String id) throws Exception {
		return ticketRepository.findByIdTicket(id);
	}
	
	
}
