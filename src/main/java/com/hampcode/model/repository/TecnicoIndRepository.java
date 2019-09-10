package com.hampcode.model.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.hampcode.model.entity.tecnicoIndependiente;

@Named

public class TecnicoIndRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "pwPU")
	private EntityManager em;

	public long insert(tecnicoIndependiente tecnicoind) throws Exception {
     em.persist(tecnicoind);
     return tecnicoind.getIdTecInd();
	}
	
	
	public Long update(tecnicoIndependiente tecnicoind) throws Exception {
		em.merge(tecnicoind);
		return tecnicoind.getIdTecInd();
	}
	
	
	public void delete(tecnicoIndependiente tecnicoind) throws Exception {
		em.remove(tecnicoind);
	}

	
	
	public List<tecnicoIndependiente> findAll() throws Exception{
		List<tecnicoIndependiente> tecnicoInd=new ArrayList<>();
		
		TypedQuery<tecnicoIndependiente> query=em.createQuery("FROM TecIndependiente t"
				,tecnicoIndependiente.class);
		tecnicoInd=query.getResultList();
		
		return tecnicoInd;
	}
	
	
	public Optional<tecnicoIndependiente> findById(Long id) throws Exception{
		tecnicoIndependiente tecnicoIndfound;
		
		TypedQuery<tecnicoIndependiente> query=em.createQuery("FROM TecIndependiente p WHERE p.id=?1"
				,tecnicoIndependiente.class);
		
		
		
		query.setParameter(1, id);
		tecnicoIndfound=query.getSingleResult();
		
		return Optional.of(tecnicoIndfound);
	}
	
	
	public List<tecnicoIndependiente> findByName(String name) throws Exception{
		List<tecnicoIndependiente> tecnicoInd=new ArrayList<>();
		
		TypedQuery<tecnicoIndependiente> query=em.createQuery("FROM TecIndependiente p WHERE p.name LIKE ?1"
				,tecnicoIndependiente.class);
		query.setParameter(1, "%"+name+"%");
		tecnicoInd=query.getResultList();
		
		return tecnicoInd;
		
	}
	
	
	
	
}
