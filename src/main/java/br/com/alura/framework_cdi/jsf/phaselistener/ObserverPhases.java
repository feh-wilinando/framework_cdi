package br.com.alura.framework_cdi.jsf.phaselistener;

import java.lang.annotation.Annotation;

import javax.enterprise.inject.Vetoed;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.enterprise.util.AnnotationLiteral;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;

import br.com.alura.framework_cdi.jsf.phaselistener.moment.After;
import br.com.alura.framework_cdi.jsf.phaselistener.moment.Before;
import br.com.alura.framework_cdi.jsf.phaselistener.phases.ApplyRequestValue;
import br.com.alura.framework_cdi.jsf.phaselistener.phases.InvokeApplication;
import br.com.alura.framework_cdi.jsf.phaselistener.phases.ProcessValidations;
import br.com.alura.framework_cdi.jsf.phaselistener.phases.RenderResponse;
import br.com.alura.framework_cdi.jsf.phaselistener.phases.RestoreView;
import br.com.alura.framework_cdi.jsf.phaselistener.phases.UpdateModelValues;

@SuppressWarnings("serial")
@Vetoed
public final class ObserverPhases {
	
	private BeanManager beanManager = CDI.current().getBeanManager();
	
	private Annotation annotationForPhase;
	private Annotation annotationForMoment;
	private PhaseEvent phaseEvent;
	
	public ObserverPhases(PhaseId id){
		
		if (PhaseId.RESTORE_VIEW.equals(id)) {
			this.annotationForPhase = new AnnotationLiteral<RestoreView>() {};
		}
		
		if (PhaseId.APPLY_REQUEST_VALUES.equals(id)) {
			this.annotationForPhase = new AnnotationLiteral<ApplyRequestValue>() {};
		}	
		
		if (PhaseId.PROCESS_VALIDATIONS.equals(id)) {
			this.annotationForPhase = new AnnotationLiteral<ProcessValidations>() {};
		}
		
		if (PhaseId.UPDATE_MODEL_VALUES.equals(id)) {
			this.annotationForPhase = new AnnotationLiteral<UpdateModelValues>() {};
		}
		
		if (PhaseId.INVOKE_APPLICATION.equals(id)) {
			this.annotationForPhase = new AnnotationLiteral<InvokeApplication>() {};
		}
		
		if (PhaseId.RENDER_RESPONSE.equals(id)) {
			this.annotationForPhase = new AnnotationLiteral<RenderResponse>() {};
		}
		
	}
						
	public ObserverPhases before() {
		this.annotationForMoment = new AnnotationLiteral<Before>() {};
		return this;
	}


	public ObserverPhases after() {
		this.annotationForMoment = new AnnotationLiteral<After>() {};
		return this;
	}


	public ObserverPhases withPhaseEvent(PhaseEvent phaseEvent){
		this.phaseEvent = phaseEvent;
		return this;
	}
	
	
						
	public void fire(){
		
		
		if (annotationForPhase != null) {
			beanManager.fireEvent(phaseEvent, annotationForMoment, annotationForPhase);			
		}else{
			beanManager.fireEvent(phaseEvent, annotationForMoment);			
		}			
		
	}

}
