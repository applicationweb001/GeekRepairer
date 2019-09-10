package com.hampcode.model.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.hampcode.model.entity.Product;

@Named
public class ProductRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "pwPU")
	private EntityManager em;

	public Long insert(Product product) throws Exception {
		em.persist(product);
		return product.getId();
	}

	public Long update(Product product) throws Exception {
		em.merge(product);
		return product.getId();
	}

	public void delete(Product product) throws Exception {
		em.remove(product);
	}

	public List<Product> findAll() throws Exception {
		List<Product> products = new ArrayList<>();

		TypedQuery<Product> query = em.createQuery("FROM Product p", Product.class);
		products = query.getResultList();

		return products;
	}

	public Optional<Product> findById(Long id) throws Exception {
		Product productFound;

		TypedQuery<Product> query = em.createQuery("FROM Product p WHERE p.id=?1", Product.class);

		query.setParameter(1, id);
		productFound = query.getSingleResult();

		return Optional.of(productFound);
	}

	public List<Product> findByName(String name) throws Exception {
		List<Product> products = new ArrayList<>();

		TypedQuery<Product> query = em.createQuery("FROM Product p WHERE p.name LIKE ?1", Product.class);
		query.setParameter(1, "%" + name + "%");
		products = query.getResultList();

		return products;
	}

}
