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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriaBusiness categoriaBusiness;

	private Categoria categoria;
	private List<Categoria> categorias;
	private Categoria categoriaSelection;
	private String filterName;

	
	@PostConstruct
	public void init() {
		categoria = new Categoria();
		categorias = new ArrayList<Categoria>();
		getAllCategorias();

	}

	public void getAllCategorias() {
		try {
			categorias = categoriaBusiness.getAll();
		} catch (Exception e) {
			Message.messageError("Error Carga de Categoria :" + e.getMessage());
		}
	}

	public String newCategoria() {
		resetForm();
		return "/categoria/insert.xhtml";
	}

	public String listCategoria() {
		return "/categoria/list.xhtml";
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
			view = "/categoria/list.xhtml";
		} catch (Exception e) {
			Message.messageError("Que pasa aqui?");

			Message.messageError("Error Product1323:" + e.getStackTrace());

			Message.messageError("Que pasa aqui?");
		}

		return view;
	}

	public String editCategoria() {
		String view = "";
		try {
			if (this.categoriaSelection != null) {
				this.categoria = this.categoriaSelection;

				view = "/categoria/update.xhtml";// Vista
			} else {
				Message.messageInfo("Debe seleccionar un cliente");
			}
		} catch (Exception e) {
			Message.messageError("Error cliente :" + e.getMessage());
		}

		return view;
	}

	public void searchCategoriaPorNombre() {
		try {

			categorias = categoriaBusiness.getCategoriaPorNombre(this.filterName.trim());
			resetForm();
			if (categorias.isEmpty()) {
				Message.messageInfo("No se encontraron clientes");

			}

		} catch (Exception e) {
			Message.messageError("Error Client Search :" + e.getMessage());
		}
	}

	public void selectCategoria(SelectEvent e) {
		this.categoriaSelection = (Categoria) e.getObject();
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

	public Categoria getCategoriaSelection() {
		return categoriaSelection;
	}

	public void setCategoriaSelection(Categoria categoriaSelection) {
		this.categoriaSelection = categoriaSelection;
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

}
