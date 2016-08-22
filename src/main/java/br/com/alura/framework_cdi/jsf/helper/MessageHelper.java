package br.com.alura.framework_cdi.jsf.helper;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;

@RequestScoped
public class MessageHelper implements Serializable {

	private static final long serialVersionUID = -1279130717985028379L;

	@Inject
	private FacesContext context;
	
	@Inject
	private Flash flash;
	
	public MessageHelper onFlash(){
		flash.setKeepMessages(true);
		return this;
	}
	
	public void addMessage(FacesMessage message){		
		context.addMessage(null, message);
	}
	public void addMessage(String clientId, FacesMessage message){
		context.addMessage(clientId, message);
	}
	
}
