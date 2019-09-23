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

import com.hampcode.model.entity.TecnicoInd;
import com.hampcode.util.Message;

@Named
@SessionScoped
public class TecnicoIndController implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private TecnicoIndBusiness tecnicoIndBusiness;

	private TecnicoInd tecnicoInd;
	private List<TecnicoInd> tecnicoInds;
	private TecnicoInd tecnicoIndSelect;
	private String filterName;

	@PostConstruct
	public void init() {
		tecnicoInd = new TecnicoInd();
		tecnicoInds = new ArrayList<TecnicoInd>();
		getAllTecnicoInds();
	}

	public void getAllTecnicoInds() {
		try {
			tecnicoInds = tecnicoIndBusiness.getAll();
		} catch (Exception e) {
			Message.messageError("Error Carga de tecnicos Independientes :" + e.getMessage());
		}
	}

	public String newTecnicoInd() {
		resetForm();
		return "/TecnicoInd/insert.xhtml";
	}

	public String listTecnicoInd() {
		return "/TecnicoInd/list.xhtml";
	}

	public String saveTecnicoInd() {
		String view = "";
		try {

			if (tecnicoInd.getId() != null) {
				tecnicoIndBusiness.update(tecnicoInd);
				Message.messageInfo("Registro actualizado exitosamente");
			} else {
				tecnicoIndBusiness.insert(tecnicoInd);
				Message.messageInfo("Registro guardado exitosamente");

			}
			this.getAllTecnicoInds();
			resetForm();
			view = "/TecnicoInd/list.xhtml";
		} catch (Exception e) {
			Message.messageError("Error TecnicoInd :" + e.getStackTrace());
		}

		return view;
	}

	public String editTecnicoInd() {
		String view = "";
		try {
			if (this.tecnicoIndSelect != null) {
				this.tecnicoInd = tecnicoIndSelect;

				view = "/TecnicoInd/update.xhtml";// Vista
			} else {
				Message.messageInfo("Debe seleccionar un tecnico");
			}
		} catch (Exception e) {
			Message.messageError("Error de TecnicoInd :" + e.getMessage());
		}

		return view;
	}

	public void searchTecnicoIndByName() {
		try {

			tecnicoInds = tecnicoIndBusiness.getTecnicoIndsByName(this.filterName.trim());
			resetForm();
			if (tecnicoInds.isEmpty()) {
				Message.messageInfo("No se encontraron Técnicos");

			}

		} catch (Exception e) {
			Message.messageError("Error Tecnico Search :" + e.getMessage());
		}
	}

	public void selectTecnicoInd(SelectEvent e) {
		this.tecnicoIndSelect = (TecnicoInd) e.getObject();
	}

	public void resetForm() {
		this.filterName = "";
		this.tecnicoInd = new TecnicoInd();
	}

	public TecnicoIndBusiness getTecnicoIndBusiness() {
		return tecnicoIndBusiness;
	}

	public void setTecnicoIndBusiness(TecnicoIndBusiness tecnicoIndBusiness) {
		this.tecnicoIndBusiness = tecnicoIndBusiness;
	}

	public TecnicoInd getTecnicoInd() {
		return tecnicoInd;
	}

	public void setTecnicoInd(TecnicoInd tecnicoInd) {
		this.tecnicoInd = tecnicoInd;
	}

	public List<TecnicoInd> getTecnicoInds() {
		return tecnicoInds;
	}

	public void setTecnicoInds(List<TecnicoInd> tecnicoInds) {
		this.tecnicoInds = tecnicoInds;
	}

	public TecnicoInd getTecnicoIndSelect() {
		return tecnicoIndSelect;
	}

	public void setTecnicoIndSelect(TecnicoInd tecnicoIndSelect) {
		this.tecnicoIndSelect = tecnicoIndSelect;
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

}
