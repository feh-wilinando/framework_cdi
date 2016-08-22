package br.com.alura.framework_cdi.jsf.security;

import java.io.Serializable;



public class AbstractLogin implements Serializable {

	private static final long serialVersionUID = -859544945084373073L;
	
	public boolean isLogged(){
		return false;
	};
	public void login(Object user){
		
		
	};
	public void logout(){
		
	};
	
	
}
