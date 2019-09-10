package com.hampcode.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.hampcode.model.entity.Especialidad;
import com.hampcode.model.repository.EspecialidadRepository;

@Named
public class EspecialidadBusiness implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EspecialidadRepository especialidadRepository;
	
	@Transactional
	public Long insert(Especialidad especialidad) throws Exception
	{
		return especialidadRepository.insert(especialidad);
	}
	
	@Transactional
	public Long update(Especialidad especialidad) throws Exception
	{
		return especialidadRepository.update(especialidad);
	}
	
	public List<Especialidad> getAll() throws Exception
	{
		return especialidadRepository.findAll();
	}
	
	public List<Especialidad> getEspecialidadByName(String nombre) throws Exception{
		return especialidadRepository.findByName(nombre);
	}
	
	
}
	
	
	
	
	
	

