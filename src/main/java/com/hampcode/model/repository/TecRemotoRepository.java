package com.hampcode.model.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


import com.hampcode.model.entity.TecRemoto;


@Named

public class TecRemotoRepository implements Serializable{

	private static final long serialVersionUID = 1L;

	
	@PersistenceContext(unitName="pwPU")
	private EntityManager em;
	
	public Long insert(TecRemoto tecnico) throws Exception {
		em.persist(tecnico);
		return tecnico.getId();
	}
	
	
	public Long update(TecRemoto tecnico) throws Exception {
		em.merge(tecnico);
		return tecnico.getId();
	}
	
	
	public void delete(TecRemoto tecnico) throws Exception {
		em.remove(tecnico);
	}
	
	
	public List<TecRemoto> findAll() throws Exception{
		List<TecRemoto> tecnicos=new ArrayList<>();
		
		TypedQuery<TecRemoto> query=em.createQuery("FROM TecRemoto tec"
				,TecRemoto.class);
		tecnicos=query.getResultList();
		
		return tecnicos;
	}
	
	
	public Optional<TecRemoto> findById(Long id) throws Exception{
		TecRemoto tecnicoFound;
		
		TypedQuery<TecRemoto> query=em.createQuery("FROM TecRemoto tec WHERE tec.id=?1"
				,TecRemoto.class);
		
		
		
		query.setParameter(1, id);
		tecnicoFound=query.getSingleResult();
		
		return Optional.of(tecnicoFound);
	}
	
	
	public List<TecRemoto> findByName(String name) throws Exception{
		List<TecRemoto> tecnicos=new ArrayList<>();
		
		TypedQuery<TecRemoto> query=em.createQuery("FROM TecRemoto tec WHERE tec.name LIKE ?1"
				,TecRemoto.class);
		query.setParameter(1, "%"+name+"%");
		tecnicos=query.getResultList();
		
		return tecnicos;
	}
	
	
}
