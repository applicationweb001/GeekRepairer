package com.hampcode.model.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.hampcode.model.entity.Especialidad;

@Named

public class EspecialidadRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "PwPU")
	private EntityManager em;
	
	
	public Long insert(Especialidad especialidad) throws Exception
	{
		em.persist(especialidad);
		return especialidad.getId();
	}
	
	public Long update(Especialidad especialidad)throws Exception
	{
		em.merge(especialidad);
		return especialidad.getId();
	}
	
	public void delete(Especialidad especialidad) throws Exception{
		em.remove(especialidad);
	}
	
	public List<Especialidad> findAll() throws Exception{
		List<Especialidad> especialidades = new ArrayList<>();
		TypedQuery<Especialidad> query = em.createQuery("From Especialidad e Where e.id=?1",
		Especialidad.class);
		
		especialidades = query.getResultList();		
		return especialidades;
	}
	
	public Optional<Especialidad> findById(Long id) throws Exception
	{
		Especialidad especialidadFound;
		
		TypedQuery<Especialidad> query = em.createQuery("From Especialidad e Where e.id =?1",
				Especialidad.class);
		
		query.setParameter(1, id);
		especialidadFound = query.getSingleResult();
		
		return Optional.of(especialidadFound);
	}
	
	public List<Especialidad> findByName(String nombre) throws Exception
	{
		List<Especialidad> especialidades = new ArrayList<>();
		
		TypedQuery<Especialidad> query = em.createQuery("From Especialidad e WHERE e.name LIKE ?1",
				Especialidad.class);
		query.setParameter(1, "%" + nombre + "%");
		especialidades = query.getResultList();
		
		return especialidades;
				
	}
	
	
}
