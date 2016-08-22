package br.com.alura.framework_cdi.jsf.producer;

import java.io.Serializable;
import java.util.Map;

import javax.enterprise.inject.Produces;
import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import br.com.alura.framework_cdi.jsf.annotation.ApplicationMap;
import br.com.alura.framework_cdi.jsf.annotation.RequestMap;
import br.com.alura.framework_cdi.jsf.annotation.SessionMap;

public class JSFProducer implements Serializable {
	
	private static final long serialVersionUID = 6629265173006839120L;

	@Produces
	public FacesContext getFacesContext(){
		return FacesContext.getCurrentInstance();
	}
	
	@Produces
	public NavigationHandler getNavigationHandler(){
		return getApplication().getNavigationHandler();
	}
	
	@Produces
	public ExternalContext getExternalContext(){
		return getFacesContext().getExternalContext();
	}
	
	@Produces	
	public Application getApplication(){
		return getFacesContext().getApplication();
	}
	
	@Produces @SessionMap
	public Map<String, Object> getSessionMap(){
		return getExternalContext().getSessionMap();
	}
	
	@Produces @ApplicationMap
	public Map<String, Object> getApplicationMap(){
		return getExternalContext().getApplicationMap();
	}
	
	@Produces @RequestMap
	public Map<String, Object> getRequestMap(){
		return getExternalContext().getRequestMap();
	}
	
	@Produces
	public Flash getFlash(){
		return getExternalContext().getFlash();
	}
	
	@Produces
	public UIViewRoot getViewRoot(){
		return getFacesContext().getViewRoot();
	}
	
	
	
}
