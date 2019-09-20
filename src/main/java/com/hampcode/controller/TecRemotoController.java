package com.hampcode.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import com.hampcode.business.TecRemotoBusiness;

import com.hampcode.model.entity.TecRemoto;
import com.hampcode.util.Message;

@Named
@SessionScoped

public class TecRemotoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TecRemotoBusiness tecnicoBusiness;

	private TecRemoto tecnico;
	private List<TecRemoto> tecnicos;
	private TecRemoto tecnicoSelect;
	private String filterName;


	public TecRemoto getTecnico() {
		return tecnico;
	}

	public void setTecnico(TecRemoto tecnico) {
		this.tecnico = tecnico;
	}

	public TecRemoto getTecnicoSelect() {
		return tecnicoSelect;
	}

	public void setTecnicoSelect(TecRemoto tecnicoSelect) {
		this.tecnicoSelect = tecnicoSelect;
	}


	@PostConstruct
	public void init() {
		tecnico = new TecRemoto();
		tecnicos = new ArrayList<TecRemoto>();
		getAllTecnicos();
	}

	public void getAllTecnicos() {
		try {
			tecnicos = tecnicoBusiness.getAll();
		} catch (Exception e) {
			Message.messageError("Error en la carga de la lista de tecnicos :" + e.getMessage());
		}
	}

	public void resetForm() {
		this.filterName = " ";
		this.tecnico = new TecRemoto();
	}

	
	public String newTecnico() {
		
		return "insert.xhtml";
	}

	public String listTecnico() {
		return "list.xhtml";
	}

	public String saveTecnico() {
		String view = "";
		try {

			if (tecnico.getId() != null) {
				tecnicoBusiness.update(tecnico);
				Message.messageInfo("Registro actualizado exitosamente");
			} else {
				tecnicoBusiness.insert(tecnico);
				Message.messageInfo("Registro guardado exitosamente");

			}
			this.getAllTecnicos();
			resetForm();
			view = "list";
		} catch (Exception e) {
			Message.messageError("Error en la carga de tecnicos :" + e.getStackTrace());
		}

		return view;
	}

	public String editTecRemoto() {
		String view = "";
		try {
			if (this.tecnicoSelect != null) {
				this.tecnico = tecnicoSelect;

				view = "/product/update";
			} else {
				Message.messageInfo("Debe seleccionar un tecnico");
			}
		} catch (Exception e) {
			Message.messageError("Error Tecnico :" + e.getMessage());
		}

		return view;
	}

	public void searchTecRemotoByName() {
		try {

			tecnicos = tecnicoBusiness.getTecnicosByName(this.filterName.trim());
			resetForm();
			if (tecnicos.isEmpty()) {
				Message.messageInfo("No se encontraron tecnicos");
			}

		} catch (Exception e) {
			Message.messageError("Error Tecnico Search :" + e.getMessage());
		}
	}

	public void selectTecRemoto(SelectEvent e) {
		this.tecnicoSelect = (TecRemoto) e.getObject();
	}


	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public TecRemotoBusiness getTecnicoBusiness() {
		return tecnicoBusiness;
	}

	public void setTecnicoBusiness(TecRemotoBusiness tecnicoBusiness) {
		this.tecnicoBusiness = tecnicoBusiness;
	}

	public List<TecRemoto> getTecnicos() {
		return tecnicos;
	}

	public void setTecnicos(List<TecRemoto> tecnicos) {
		this.tecnicos = tecnicos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
