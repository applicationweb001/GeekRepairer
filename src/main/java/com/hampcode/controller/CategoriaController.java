package com.hampcode.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.hampcode.business.CategoriaBusiness;
import com.hampcode.model.entity.Categoria;
import com.hampcode.util.Message;

@Named
@SessionScoped
public class CategoriaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriaBusiness categoriaBusiness;
	private Categoria categoria;
	private List<Categoria> categorias;
	private Categoria categoriaSelect;
	private String filterName;

	@PostConstruct
	public void init() {
		categoria = new Categoria();
		categorias = new ArrayList<Categoria>();
		getAllCategorias();
	}

	public void resetForm() {
		this.filterName = "";
		this.categoria = new Categoria();
	}

	public void getAllCategorias() {
		try {
			categorias = categoriaBusiness.getAll();
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
			view = "list";
		} catch (Exception e) {
			Message.messageError("Error Product :" + e.getStackTrace());
		}

		return view;
	}

	public String editProduct() {
		String view = "";
		try {
			if (this.categoriaSelect != null) {
				this.categoria = categoriaSelect;

				view = "aun por ver el nombre";// Vista
			} else {
				Message.messageInfo("Debe seleccionar un Categoria");
			}
		} catch (Exception e) {
			Message.messageError("Error Categoria :" + e.getMessage());
		}

		return view;
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