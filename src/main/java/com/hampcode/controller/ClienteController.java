package com.hampcode.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.hampcode.business.ClienteBusiness;
import com.hampcode.model.entity.Client;
import com.hampcode.util.Message;

@Named
@SessionScoped
public class ClienteController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteBusiness clienteBusiness;

	private Client cliente;
	private List<Client> clientes;
	private Client clienteSeleccion;
	private String filtradocliente;

	@PostConstruct
	public void init() {
		cliente = new Client();
		clientes = new ArrayList<Client>();
		//getAllClientes();

	}

	public void getAllClientes() {
		try {
			clientes = clienteBusiness.getAll();
		} catch (Exception e) {
			Message.messageError("Error Carga de Clientes :" + e.getMessage());
		}
	}

	public String newClient() {
		resetForm();
		return "insert.xhtml";
	}

	public String listClient() {
		return "list.xhtml";
	}

	public String saveCliente() {
		String view = "";
		try {

			if (cliente.getIdCliente() != null) {
				clienteBusiness.update(cliente);
				Message.messageInfo("Registro actualizado exitosamente");
			} else {
				clienteBusiness.insert(cliente);
				Message.messageInfo("Registro guardado exitosamente");

			}
			//this.getAllClientes();
			resetForm();
			view = "list";
		} catch (Exception e) {
			Message.messageError("Error Product :" + e.getStackTrace());
		}

		return view;
	}

	public String editClient() {
		String view = "";
		try {
			if (this.clienteSeleccion != null) {
				this.cliente = clienteSeleccion;

				view = "update";// Vista
			} else {
				Message.messageInfo("Debe seleccionar un cliente");
			}
		} catch (Exception e) {
			Message.messageError("Error cliente :" + e.getMessage());
		}

		return view;
	}

	public void searchClientePorNombre() {
		try {

			clientes = clienteBusiness.getClientePorNombre(this.filtradocliente.trim());
			resetForm();
			if (clientes.isEmpty()) {
				Message.messageInfo("No se encontraron clientes");

			}

		} catch (Exception e) {
			Message.messageError("Error Client Search :" + e.getMessage());
		}
	}

	public void resetForm() {
		this.filtradocliente = "";
		this.cliente = new Client();

	}

	public ClienteBusiness getClienteBusiness() {
		return clienteBusiness;
	}

	public void setClienteBusiness(ClienteBusiness clienteBusiness) {
		this.clienteBusiness = clienteBusiness;
	}

	public Client getCliente() {
		return cliente;
	}

	public void setCliente(Client cliente) {
		this.cliente = cliente;
	}

	public List<Client> getClientes() {
		return clientes;
	}

	public void setClientes(List<Client> clientes) {
		this.clientes = clientes;
	}

	public Client getClienteSeleccion() {
		return clienteSeleccion;
	}

	public void setClienteSeleccion(Client clienteSeleccion) {
		this.clienteSeleccion = clienteSeleccion;
	}

	public String getFiltradocliente() {
		return filtradocliente;
	}

	public void setFiltradocliente(String filtradocliente) {
		this.filtradocliente = filtradocliente;
	}

}
