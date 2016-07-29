package br.com.alura.framework_cdi.jsf.phaselistener;

import java.util.List;

import javax.enterprise.inject.Vetoed;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import br.com.alura.framework_cdi.jsf.phaselistener.security.SafePages;
import br.com.alura.framework_cdi.jsf.security.LoggedUser;
import br.com.alura.framework_cdi.jsf.security.Role;

@Vetoed
public class SecurityPhaseListener implements PhaseListener {

	private static final long serialVersionUID = -4297095393000187473L;

	@Inject
	private UIViewRoot viewRoot;

	@Inject
	private NavigationHandler navigationHandler;

	@Inject
	private FacesContext context;

	@Inject
	@SafePages
	private List<String> safePages;

	@Inject
	@LoginPage
	private String loginPage;

	@Inject
	private LoggedUser loggedUser;

	@Override
	public void afterPhase(PhaseEvent event) {

		if (isSafePage()) {
			return;
		}
							
		
		if (loggedUser.isLogged() && hasRole()) {
			return;
		} else {
			
			loggedUser.logout();
			
			navigationHandler.handleNavigation(context, null, loginPage);

			context.renderResponse();
		}

	}

	private boolean hasRole() {
		return loggedUser.getRoles().contains(getRoleForPage());
	}

	@SuppressWarnings("unchecked")
	private Role getRoleForPage() {
		BeanManager beanManager = CDI.current().getBeanManager();		
		Bean<Role> bean = (Bean<Role>) beanManager.resolve(beanManager.getBeans(Role.class));
		Role role = (Role) beanManager.getReference(bean, bean.getBeanClass(), beanManager.createCreationalContext(bean));
		return role;
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

	private boolean isSafePage() {
		return safePages.contains(viewRoot.getViewId());
	}

}
