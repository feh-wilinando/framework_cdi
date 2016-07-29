package br.com.alura.framework_cdi.jsf.factory;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

@Alternative
public class JSFFactory {
	
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
	
	@Produces
	public Flash getFlash(){
		return getExternalContext().getFlash();
	}
	
	@Produces
	public UIViewRoot getViewRoot(){
		return getFacesContext().getViewRoot();
	}
	
	
	
}
