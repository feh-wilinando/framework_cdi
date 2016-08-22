package br.com.alura.framework_cdi.jsf.phaselistener;

import javax.faces.application.NavigationHandler;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.alura.framework_cdi.jsf.producer.JSFProducer;
import br.com.alura.framework_cdi.jsf.security.AbstractLogin;
import br.com.alura.framework_cdi.util.Lookup;;

public class SecurityPhaseListener implements PhaseListener {

	private static final long serialVersionUID = -4297095393000187473L;	
	
	private Lookup<AbstractLogin> lookupAbstractLogin = new Lookup<AbstractLogin>(AbstractLogin.class);
	private Lookup<JSFProducer> lookupJSFProducer = new Lookup<JSFProducer>(JSFProducer.class);	
		
	@Override
	public void afterPhase(PhaseEvent event) {
		JSFProducer jsfProducer = lookupJSFProducer.getBean();
		AbstractLogin abstractLogin = lookupAbstractLogin.getBean();
		
		if (jsfProducer.getViewRoot().getViewId().equals("/login.xhtml")) {
			return;
		}
		
		if (!abstractLogin.isLogged()) {
			
			NavigationHandler navigationHandler = jsfProducer.getNavigationHandler();
			
			navigationHandler.handleNavigation(jsfProducer.getFacesContext(), null, "login?face-redirect=true");
			
			jsfProducer.getFacesContext().renderResponse();
					
		}
		
	}


	@Override
	public void beforePhase(PhaseEvent event) {}

	
	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
