package br.com.alura.framework_cdi.jsf.phaselistener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;


public class GenericPhaseListener implements PhaseListener{

	private static final long serialVersionUID = 6238949505723585431L;
	
	
	@Override
	public void afterPhase(PhaseEvent phaseEvent) {											
		
		new ObserverPhases(phaseEvent.getPhaseId())
			.after()
				.withPhaseEvent(phaseEvent)
					.fire();
	}

	@Override
	public void beforePhase(PhaseEvent phaseEvent) {
		
		new ObserverPhases(phaseEvent.getPhaseId())
			.before()
				.withPhaseEvent(phaseEvent)
					.fire();				
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
	
	
}
