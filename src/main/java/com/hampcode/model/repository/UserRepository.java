package com.hampcode.model.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.hampcode.model.entity.User;

@Named

public class UserRepository implements IUserRepository, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "VisorPU")
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public User login(User u) throws Exception{
		
		User user = null;
		Query query = em.createQuery("FROM User u WHERE u.username = ?1 and u.password = ?2");
		
		query.setParameter(1,u.getUsername());
		query.setParameter(2,u.getPassword());
		
		List<User> users = query.getResultList();
		if(!users.isEmpty())
			user = users.get(0);
			
			return user;			
	}
	
	
	
	
}
