package br.com.alura.framework_cdi.jsf.security;

import java.util.List;

public interface LoggedUser {
	boolean isLogged();
	
	void logout();
	void login();
	
	List<Role> getRoles();
}
