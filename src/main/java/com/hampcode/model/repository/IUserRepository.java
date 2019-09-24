package com.hampcode.model.repository;

import com.hampcode.model.entity.User;

public interface IUserRepository {

	User login(User u)throws Exception;
	
}
