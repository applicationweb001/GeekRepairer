package com.hampcode.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.hampcode.model.entity.tecnicoIndependiente;
import com.hampcode.model.repository.TecnicoIndRepository;

@Named

public class TecnicoIndBusiness implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private TecnicoIndRepository tecnicoIndRepository;

	@Transactional
	public long insert(tecnicoIndependiente tecnicoInd) throws Exception {
		return tecnicoIndRepository.insert(tecnicoInd);
	}

	@Transactional
	public Long update(tecnicoIndependiente tecnicoInd) throws Exception {
		return tecnicoIndRepository.update(tecnicoInd);
	}

	public List<tecnicoIndependiente> getAll() throws Exception {
		return tecnicoIndRepository.findAll();
	}

	public List<tecnicoIndependiente> gettecnicoIndByName(String name) throws Exception {
		return tecnicoIndRepository.findByName(name);
	}

}
