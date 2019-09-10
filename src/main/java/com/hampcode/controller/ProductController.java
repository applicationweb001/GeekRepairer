package com.hampcode.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import com.hampcode.business.ProductBusiness;
import com.hampcode.model.entity.Product;
import com.hampcode.util.Message;

@Named
@SessionScoped
public class ProductController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProductBusiness productBusiness;

	private Product product; // NuevoProducto
	private List<Product> products;// ListaProductos
	private Product productSelect;// Producto Seleccionado Editar
	private String filterName;// Criterio de Busqueda

	@PostConstruct
	public void init() {
		product = new Product();
		products = new ArrayList<Product>();
		getAllProducts();
	}

	public void getAllProducts() {
		try {
			products = productBusiness.getAll();
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

	public String saveProduct() {
		String view = "";
		try {

			if (product.getId() != null) {
				productBusiness.update(product);
				Message.messageInfo("Registro actualizado exitosamente");
			} else {
				productBusiness.insert(product);
				Message.messageInfo("Registro guardado exitosamente");

			}
			this.getAllProducts();
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
			if (this.productSelect != null) {
				this.product = productSelect;

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

			products = productBusiness.getProductsByName(this.filterName.trim());
			resetForm();
			if (products.isEmpty()) {
				Message.messageInfo("No se encontraron productos");

			}

		} catch (Exception e) {
			Message.messageError("Error Product Search :" + e.getMessage());
		}
	}

	public void selectProduct(SelectEvent e) {
		this.productSelect = (Product) e.getObject();
	}

	public void resetForm() {
		this.filterName = "";
		this.product = new Product();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product getProductSelect() {
		return productSelect;
	}

	public void setProductSelect(Product productSelect) {
		this.productSelect = productSelect;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

}
