package com.hampcode.business;

import java.io.Serializable;

import javax.inject.Inject;

import com.hampcode.model.entity.User;
import com.hampcode.model.repository.IUserRepository;

public class UserBusiness implements IUserBusiness,Serializable{

	private static final long serialVersionUID = 1L;
	@Inject 	
	private IUserRepository userRepository;
	
	@Override
	public User login(User u)throws Exception{
		return userRepository.login(u);
	}
	
	
	
}
