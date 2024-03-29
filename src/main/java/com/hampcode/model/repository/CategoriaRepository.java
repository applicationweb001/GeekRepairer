package com.hampcode.model.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.hampcode.model.entity.Categoria;

public class CategoriaRepository implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "pwPU")
	private EntityManager em;

	public Long insert(Categoria categoria) throws Exception {
		em.persist(categoria);
		return categoria.getId();
	}

	public Long update(Categoria categoria) throws Exception {
		em.merge(categoria);
		return categoria.getId();
	}

	public void delete(Categoria categoria) throws Exception {
		em.remove(categoria);
	}

	public List<Categoria> findAll() throws Exception {
		List<Categoria> categorias = new ArrayList<>();

		TypedQuery<Categoria> query = em.createQuery("FROM Categoria c", Categoria.class);
		categorias = query.getResultList();

		return categorias;
	}

	public Optional<Categoria> findById(Long id) throws Exception {
		Categoria categoriaFound;

		TypedQuery<Categoria> query = em.createQuery("FROM Categoria p WHERE p.id=?1", Categoria.class);

		query.setParameter(1, id);
		categoriaFound = query.getSingleResult();

		return Optional.of(categoriaFound);
	}

	public List<Categoria> findByName(String name) throws Exception {
		List<Categoria> categorias = new ArrayList<>();

		TypedQuery<Categoria> query = em.createQuery("FROM Categoria p WHERE p.name LIKE ?1", Categoria.class);
		query.setParameter(1, "%" + name + "%");
		categorias = query.getResultList();

		return categorias;
	}

}
