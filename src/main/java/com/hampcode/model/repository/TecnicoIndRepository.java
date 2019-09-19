package com.hampcode.model.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.hampcode.model.entity.TecnicoInd;

@Named
public class TecnicoIndRepository implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
@PersistenceContext (unitName="pwPU")
private EntityManager em;

public Long insert(TecnicoInd tecnicoInd) throws Exception
{
	em.persist(tecnicoInd);
	return tecnicoInd.getId();
}

public Long update(TecnicoInd tecnicoInd) throws Exception {
	em.merge(tecnicoInd);
	return tecnicoInd.getId();
}

public void delete(TecnicoInd tecnicoInd) throws Exception {
	em.remove(tecnicoInd);
}


public List<TecnicoInd> findAll() throws Exception{
	List<TecnicoInd> tecnicoInds=new ArrayList<>();
	
	TypedQuery<TecnicoInd> query=em.createQuery("FROM TecnicoInd t"
			,TecnicoInd.class);
	tecnicoInds=query.getResultList();
	
	return tecnicoInds;
}

public Optional<TecnicoInd> findById(Long id) throws Exception{
	TecnicoInd tecnicoFound;
	
	TypedQuery<TecnicoInd> query=em.createQuery("FROM TecnicoInd p WHERE p.id=?1"
			,TecnicoInd.class);
	
	
	
	query.setParameter(1, id);
	tecnicoFound=query.getSingleResult();
	
	return Optional.of(tecnicoFound);
	
}

public List<TecnicoInd> findByName(String name) throws Exception{
	List<TecnicoInd> tecnicoInds=new ArrayList<>();
	
	TypedQuery<TecnicoInd> query=em.createQuery("FROM TecnicoInd p WHERE p.name LIKE ?1"
			,TecnicoInd.class);
	query.setParameter(1, "%"+name+"%");
	tecnicoInds=query.getResultList();
	
	return tecnicoInds;
}

	
}
