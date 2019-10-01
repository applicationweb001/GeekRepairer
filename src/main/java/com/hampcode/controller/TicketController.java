package com.hampcode.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.hampcode.business.ClienteBusiness;
import com.hampcode.business.TicketBusiness;
import com.hampcode.model.entity.Cliente;
import com.hampcode.model.entity.Ticket;
import com.hampcode.util.Message;

@Named
@SessionScoped

public class TicketController implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private TicketBusiness ticketBusiness;
	
	@Inject
	private ClienteBusiness clienteBusiness;
	
	private Cliente cliente;
	private Ticket ticket;
	private List<Ticket> tickets;
	private List<Ticket> cola;
	private List<Cliente> clientes;
	
	private Ticket ticketSelect;
	private Cliente clienteSelect;
	
	private String filterNameCliente;
	private String filterApellidoCliente;
	
	

	@PostConstruct
	public void init() {
		ticket = new Ticket();
		tickets = new ArrayList<Ticket>();
		clientes = new ArrayList<Cliente>();
		getAllTickets();
		getAllTicketsCola();
		
	}

	public void getAllTickets() {
		try {
			tickets = ticketBusiness.getAll();
		} catch (Exception e) {
			Message.messageError("Error en la carga de la lista de tickets  :" + e.getMessage());
		}
	}
	public void getAllTicketsCola() {
		try {
			cola = ticketBusiness.getAllCola();
		} catch (Exception e) {
			Message.messageError("Error en la carga de la lista de tickets en cola :" + e.getMessage());
		}
	}
	

	public void resetForm() {
	
		this.filterApellidoCliente ="";
		this.filterNameCliente ="";
		this.ticket = new Ticket();
		this.cliente = new Cliente();
		
	}

	
	public String newTicket() {
		
		return "/Ticket/insert.xhtml";
	}

	public String listTicket() {
		return "/Ticket/list.xhtml";
	}

	public String saveTicket() {
		String view = "";
		try {

			if (ticket.getIdTicket() != null) {
				ticket.setCliente(cliente);
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
	
	public void searchClienteByAll() {
		try {

			clientes = this.clienteBusiness.getByAll(this.filterNameCliente, this.filterApellidoCliente);
			resetForm();
			if (clientes.isEmpty()) {
				Message.messageInfo("No se encontraron Clientes");
			}

		} catch (Exception e) {
			Message.messageError("Error Tecnico Search :" + e.getMessage());
		}
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

	public List<Ticket> getCola() {
		return cola;
	}

	public void setCola(List<Ticket> cola) {
		this.cola = cola;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getFilterNameCliente() {
		return filterNameCliente;
	}

	public void setFilterNameCliente(String filterNameCliente) {
		this.filterNameCliente = filterNameCliente;
	}


	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente getClienteSelect() {
		return clienteSelect;
	}

	public void setClienteSelect(Cliente clienteSelect) {
		this.clienteSelect = clienteSelect;
	}

	public String getFilterApellidoCliente() {
		return filterApellidoCliente;
	}

	public void setFilterApellidoCliente(String filterApellidoCliente) {
		this.filterApellidoCliente = filterApellidoCliente;
	}

	
	
}
