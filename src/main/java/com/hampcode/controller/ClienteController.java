package com.hampcode.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import com.hampcode.business.ClienteBusiness;
import com.hampcode.model.entity.Cliente;
import com.hampcode.util.Message;

@Named
@SessionScoped
public class ClienteController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ClienteBusiness clienteBusiness;
	
	private Cliente cliente;
	private List<Cliente> clientes;
	private Cliente clienteSelection;
	private String filterName;
	
	@PostConstruct
	public void init() {
		cliente=new Cliente();
		clientes=new ArrayList<Cliente>();
		getAllClientes();
		
	}
	
	public void getAllClientes() {
		try {
			clientes = clienteBusiness.getAll();
		} catch (Exception e) {
			Message.messageError("Error Carga de Clientes :" + e.getMessage());
		}
	}
	
	public String newCliente() {
		resetForm();
		return "/cliente/insert.xhtml";
	}

	public String listCliente() {
		return "/cliente/list.xhtml";
	}
	
	public String saveCliente() {
		String view = "";
		try {

			if (cliente.getId() != null) {
				clienteBusiness.update(cliente);
				Message.messageInfo("Registro actualizado exitosamente");
			} else {
				clienteBusiness.insert(cliente);
				Message.messageInfo("Registro guardado exitosamente");

			}
			this.getAllClientes();
			resetForm();
			view = "/cliente/list.xhtml";
		} 
		catch (Exception e) {
			Message.messageError("Que pasa aqui?");
				
			Message.messageError("Error Product1323:" + e.getStackTrace());
			
			Message.messageError("Que pasa aqui?");
		}

		return view;
	}
	public String editCliente() {
		String view = "";
		try {
			if (this.clienteSelection != null) {
				this.cliente = clienteSelection;

				view = "/cliente/update.xhtml";// Vista
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

			clientes = clienteBusiness.getClientePorNombre(this.filterName.trim());
			resetForm();
			if (clientes.isEmpty()) {
				Message.messageInfo("No se encontraron clientes");

			}

		} catch (Exception e) {
			Message.messageError("Error Client Search :" + e.getMessage());
		}
	}

	public void selectCliente(SelectEvent e)
	{
		this.cliente = (Cliente) e.getObject();
	}
	
	
	public void resetForm() {
		this.filterName="";
		this.cliente = new Cliente();
		
	}

	public ClienteBusiness getClienteBusiness() {
		return clienteBusiness;
	}

	public void setClienteBusiness(ClienteBusiness clienteBusiness) {
		this.clienteBusiness = clienteBusiness;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente getClienteSelection() {
		return clienteSelection;
	}

	public void setClienteSelection(Cliente clienteSelection) {
		this.clienteSelection = clienteSelection;
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

}
