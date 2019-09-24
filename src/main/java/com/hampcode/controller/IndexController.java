package com.hampcode.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.hampcode.business.IUserBusiness;
import com.hampcode.model.entity.Role;
import com.hampcode.model.entity.User;

@Named
@ViewScoped
public class IndexController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject 
	private IUserBusiness userBusiness;
	private User user;
	
	public void init() {
		this.user = new User();
	}
	
	public String login() {
		String redirect = null;
		try {
			User user = this.userBusiness.login(this.user);
			
			if(user != null) {
				if(user.getRole().equals(Role.ROLE_ADMIN))
				{
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user",user)
					redirect = "-indice";
				} else if(user.getRole().equals(Role.ROLE_USER)){
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user",user);
					redirect = "-indice";
				}				
			}else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Credenciales incorrectas","Credenciales incorrectas"));
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return redirect;
		
	}
	
	
	public void checkSesion() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			User user = (User) context.getExternalContext().getSessionMap().get("user");
			
			if(user == null) {
				context.getExternalContext().redirect("-indice");
			}
			
		}catch(Exception e) {
			
		}
	}
	
	
	public void closeSession() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
