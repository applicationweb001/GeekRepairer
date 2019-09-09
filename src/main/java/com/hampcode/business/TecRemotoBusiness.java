package com.hampcode.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.hampcode.model.entity.TecRemoto;
import com.hampcode.model.repository.TecRemotoRepository;

@Named
public class TecRemotoBusiness implements Serializable{


	
	//dfgdfdgf
	private static final long serialVersionUID = 1L;
	
	@Inject
	private TecRemotoRepository tecnicoRepository;

	@Transactional 
	public Long insert(TecRemoto tecnico) throws Exception {
		return tecnicoRepository.insert(tecnico);
	}

	
	@Transactional
	public Long update(TecRemoto tecnico) throws Exception{
		return tecnicoRepository.update(tecnico);
	}
	
	
	public List<TecRemoto> getAll() throws Exception {
		return tecnicoRepository.findAll();
	}
	
	
	public List<TecRemoto> getTecnicosByName(String name) throws Exception{
		return tecnicoRepository.findByName(name);
	}

	
	
	
}
