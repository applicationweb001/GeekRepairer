package com.hampcode.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import com.hampcode.business.CategoriaBusiness;
import com.hampcode.model.entity.Categoria;
import com.hampcode.util.Message;

@Named
@SessionScoped
public class CategoriaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriaBusiness categoriaBusiness;
	
	private Categoria categoria;
	private List<Categoria> categorias;
	private Categoria categoriaSelect;
	private String filterName;

	@PostConstruct
	public void init(){
		
		categoria = new Categoria();
		categorias = new ArrayList<Categoria>();
		this.getAllCategorias();
	}
	
	
	public String newCategoria() {
		resetForm();
		return "/Categoria/insert.xhtml";
	}

	public String listCategoria() {
		return "/Categoria/list.xhtml";
	}

	public void getAllCategorias() {
		try {
			categorias = this.categoriaBusiness.getAll();
		} catch (Exception e) {
			Message.messageError("Error Carga de Categorias :" + e.getMessage());
		}
	}

	public String saveCategoria() {
		String view = "";
		try {

			if (categoria.getId() != null) {
				categoriaBusiness.update(categoria);
				Message.messageInfo("Registro actualizado exitosamente");
			} else {
				categoriaBusiness.insert(categoria);
				Message.messageInfo("Registro guardado exitosamente");

			}
			this.getAllCategorias();
			resetForm();
			view = "/Categoria/list.xhtml";
		} catch (Exception e) {
			Message.messageError("Error Product :" + e.getStackTrace());
		}

		return view;
	}

	public String editCategoria() {
		String view = "";
		try {
			if (this.categoriaSelect != null) {
				this.categoria = categoriaSelect;

				view = "/Categoria/update";// Vista
			} else {
				Message.messageInfo("Debe seleccionar un Categoria");
			}
		} catch (Exception e) {
			Message.messageError("Error Categoria :" + e.getMessage());
		}

		return view;
	}
	
	public void selectCategoria(SelectEvent e)
	{
		this.categoria = (Categoria) e.getObject();
	}
	
	
	
	public void searchCategoriaPorNombre() {
		try {

			categorias = categoriaBusiness.getCategoriaPorNombre(this.filterName.trim());
			resetForm();
			if (categorias.isEmpty()) {
				Message.messageInfo("No se encontraron Categorias");

			}

		} catch (Exception e) {
			Message.messageError("Error Categoria Search :" + e.getMessage());
		}
	}

	public void resetForm() {
		this.filterName = "";
		this.categoria = new Categoria();
	}
	

	public CategoriaBusiness getCategoriaBusiness() {
		return categoriaBusiness;
	}

	public void setCategoriaBusiness(CategoriaBusiness categoriaBusiness) {
		this.categoriaBusiness = categoriaBusiness;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Categoria getCategoriaSelect() {
		return categoriaSelect;
	}

	public void setCategoriaSelect(Categoria categoriaSelect) {
		this.categoriaSelect = categoriaSelect;
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

}
