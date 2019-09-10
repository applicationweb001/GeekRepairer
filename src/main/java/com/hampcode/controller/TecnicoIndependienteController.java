package com.hampcode.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import com.hampcode.business.TecnicoIndBusiness;
import com.hampcode.model.entity.tecnicoIndependiente;
import com.hampcode.util.Message;

@Named
@SessionScoped

public class TecnicoIndependienteController implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private TecnicoIndBusiness tecnicobusiness;
	
	private tecnicoIndependiente tecnicoInd; //NuevoProducto
	private List<tecnicoIndependiente> tecnicoInds;//ListaProductos
	private tecnicoIndependiente tecnicoselect;//Producto Seleccionado Editar
	private String filterName;// Criterio de Busqueda
	
	@PostConstruct
	public void init() {
		tecnicoInd = new tecnicoIndependiente();
		tecnicoInds = new ArrayList<tecnicoIndependiente>();
		getAlltecnicos();
	}

	public void getAlltecnicos() {
		try {
			tecnicoInds= tecnicobusiness.getAll();
			
		} catch (Exception e) {
			Message.messageError("Error Carga de Productos :" + e.getMessage());
		}
	}

	public String newProduct() {
		resetForm();
		return "insert.xhtml";
	}

	public String listProduct() {
		return "list.xhtml";
	}

	public String saveTecnicoInd() {
		String view = "";
		try {
			if (this.tecnicoInd.getIdTecInd()!= null ){
				tecnicobusiness.update(tecnicoInd);
				Message.messageInfo("Registro actualizado exitosamente");
			} 
			else {
				tecnicobusiness.insert(tecnicoInd);
				Message.messageInfo("Registro guardado exitosamente");

			}
			this.getAlltecnicos();
			resetForm();
			view = "list";
		} catch (Exception e) {
			Message.messageError("Error Product :" + e.getStackTrace());
		}

		return view;
	}

	public String edittecnicoInd() {
		String view = "";
		try {
			if (this.tecnicoselect != null) {
				this.tecnicoInd = tecnicoselect;

				view = "update";// Vista
			} else {
				Message.messageInfo("Debe seleccionar un producto");
			}
		} catch (Exception e) {
			Message.messageError("Error Product :" + e.getMessage());
		}

		return view;
	}

	public void searchProductByName() {
		try {

			tecnicoInds = tecnicobusiness.gettecnicoIndByName(this.filterName.trim());
			resetForm();
			if (tecnicoInds.isEmpty()) {
				Message.messageInfo("No se encontraron productos");

			}

		} catch (Exception e) {
			Message.messageError("Error Product Search :" + e.getMessage());
		}
	}

	public void selectTecnicoInd(SelectEvent e) {
		this.tecnicoselect = (tecnicoIndependiente) e.getObject();
	}

	public void resetForm() {
		this.filterName="";
		this.tecnicoInd = new tecnicoIndependiente();
	}

	

}
