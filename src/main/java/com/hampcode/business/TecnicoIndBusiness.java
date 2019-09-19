package com.hampcode.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;


import com.hampcode.model.entity.TecnicoInd;
import com.hampcode.model.repository.TecnicoIndRepository;

@Named
public class TecnicoIndBusiness implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private TecnicoIndRepository tecnicoIndRepository;

	@Transactional
	public Long insert(TecnicoInd tecnicoInd) throws Exception {
		return tecnicoIndRepository.insert(tecnicoInd);
	}
	
	@Transactional
	public Long update(TecnicoInd tecnicoInd) throws Exception {
		return tecnicoIndRepository.update(tecnicoInd);
	}

	public List<TecnicoInd> getAll() throws Exception {
		return tecnicoIndRepository.findAll();
	}

	public List<TecnicoInd> getTecnicoIndsByName(String name) throws Exception {
		return tecnicoIndRepository.findByName(name);
	}

}
