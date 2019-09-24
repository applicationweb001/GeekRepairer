package com.hampcode.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;


import com.hampcode.business.TicketBusiness;
import com.hampcode.model.entity.Ticket;
import com.hampcode.util.Message;

@Named
@SessionScoped

public class TicketController implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private TicketBusiness ticketBusiness;

	private Ticket ticket;
	private List<Ticket> tickets;
	private Ticket ticketSelect;
	

	@PostConstruct
	public void init() {
		ticket = new Ticket();
		tickets = new ArrayList<Ticket>();
		getAllTickets();
	}

	public void getAllTickets() {
		try {
			tickets = ticketBusiness.getAll();
		} catch (Exception e) {
			Message.messageError("Error en la carga de la lista de tecnicos :" + e.getMessage());
		}
	}

	public void resetForm() {
	
		this.ticket = new Ticket();
	}

	
	public String newTicket() {
		
		return "/Ticket/insert.xhtml";
	}

	public String listTicket() {
		return "/Ticket/list.xhtml";
	}

	public String saveTecnico() {
		String view = "";
		try {

			if (ticket.getIdTicket() != null) {
				this.ticketBusiness.update(ticket);
				Message.messageInfo("Se actualizaron los datos satisfactoriamente");
			} else {
				this.ticketBusiness.insert(ticket);
				Message.messageInfo("El Registro se realizo satisfactoriamente");

			}
			this.getAllTickets();
			resetForm();
			view = "/Ticket/list";
		} catch (Exception e) {
			Message.messageError("Error en la carga de tickets :" + e.getStackTrace());
		}

		return view;
	}

	public String editTicket() {
		String view = "";
		try {
			if (this.ticketSelect != null) {
				this.ticket = ticketSelect;

				view = "/Ticket/update";
			} else {
				Message.messageInfo("Debe seleccionar un Ticket");
			}
		} catch (Exception e) {
			Message.messageError("Error Ticket :" + e.getMessage());
		}

		return view;
	}

	public TicketBusiness getTicketBusiness() {
		return ticketBusiness;
	}

	public void setTicketBusiness(TicketBusiness ticketBusiness) {
		this.ticketBusiness = ticketBusiness;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket getTicketSelect() {
		return ticketSelect;
	}

	public void setTicketSelect(Ticket ticketSelect) {
		this.ticketSelect = ticketSelect;
	}

	
}
