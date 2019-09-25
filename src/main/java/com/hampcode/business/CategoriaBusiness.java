package com.hampcode.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.hampcode.model.entity.Categoria;
import com.hampcode.model.repository.CategoriaRepository;

@Named
public class CategoriaBusiness implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriaRepository categoriaRepository;

	@Transactional
	public Long insert(Categoria categoria) throws Exception {
		return categoriaRepository.insert(categoria);
	}

	@Transactional
	public Long update(Categoria categoria) throws Exception {
		return categoriaRepository.update(categoria);
	}

	public List<Categoria> getAll() throws Exception {
		return categoriaRepository.findAll();
	}
	
	public List<Categoria> getCategoriaPorNombre(String nombre) throws Exception {
		return categoriaRepository.findByName(nombre);
	}

}