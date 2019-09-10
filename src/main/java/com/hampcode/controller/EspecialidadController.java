package com.hampcode.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import com.hampcode.business.EspecialidadBusiness;
import com.hampcode.model.entity.Especialidad;
import com.hampcode.util.Message;

@Named
@SessionScoped

public class EspecialidadController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EspecialidadBusiness especialidadBusiness;
	
	private Especialidad especialidad;
	private List<Especialidad> especialidades;
	private Especialidad especialidadSelect;
	private String filterName;
	
	@PostConstruct
	public void init() {
		especialidad = new Especialidad();
		especialidades = new ArrayList<Especialidad>();
		getAllEspecialidades();
	}
	
	public void getAllEspecialidades()
	{
		try
		{
			especialidades = especialidadBusiness.getAll();
		} catch(Exception e)
		{
			Message.messageError("Error Carga de Especialidades: " + e.getMessage());
		}
	}
	
	public String newEspecialidad()
	{
		resetForm();
		return "insert.xhtml";
	}
	
	public String listEspecialidad() {
		return "list.xhtml";
	}
		
	public String saveEspecialidad() {
		String view = "";
		try
		{
			if(especialidad.getId() != null) {
			especialidadBusiness.update(especialidad);
			Message.messageInfo("Registro actualizado exitosamente");
			
		} else {
			especialidadBusiness.insert(especialidad);
			Message.messageInfo("Registro guardado exitosamente");
		}
		this.getAllEspecialidades();
		resetForm();
		view = "list";
		
	}catch(Exception e)
		{
		Message.messageInfo("Error Especialidad :" + e.getStackTrace());
		}
		return view;
	}
	
	public String editEspecialidad() {
		String view = "";
		try {
			if(this.especialidadSelect != null) {
				this.especialidad = especialidadSelect;
				
				view = "update";
			}else {
				Message.messageInfo("Debe seleccionar una especialidad");
			}
		} catch(Exception e)
		{
			Message.messageError("Error Especialidad:" + e.getMessage());
		}
		return view;
	}
	
	public void searchEspecialidadByName() {
	try {		
		especialidades = especialidadBusiness.getEspecialidadByName(this.filterName.trim());
		resetForm();
		if(especialidades.isEmpty()) {
			Message.messageInfo("No se encontraron especialidades");
		}
	} catch(Exception e) {
		Message.messageError("Error Especialidad Search" + e.getMessage());
		}
	}
	
	public void selectEspecialidad(SelectEvent e) {
		this.especialidadSelect = (Especialidad) e.getObject();
	}
		
	public void resetForm() {
		this.filterName="";
		this.especialidad = new Especialidad();
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}
	
	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
	
	public List<Especialidad> getEspecialidades(){
		return especialidades;
	}

	public EspecialidadBusiness getEspecialidadBusiness() {
		return especialidadBusiness;
	}

	public void setEspecialidadBusiness(EspecialidadBusiness especialidadBusiness) {
		this.especialidadBusiness = especialidadBusiness;
	}

	public Especialidad getEspecialidadSelect() {
		return especialidadSelect;
	}

	public void setEspecialidadSelect(Especialidad especialidadSelect) {
		this.especialidadSelect = especialidadSelect;
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setEspecialidades(List<Especialidad> especialidades) {
		this.especialidades = especialidades;
	}
	
	
}
