package br.com.alura.framework_cdi.interceptors.tx;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Interceptor
@Trasactional
public class ManagerTransaction {

	@Inject
	private Instance<EntityManager> instanceManager;
		
	public Object executeWithTransaction(InvocationContext context){
		
		EntityManager manager = instanceManager.get();
		
		
		manager.getTransaction().begin();
		
		Object result;
		
		try {
			result = context.proceed();
		} catch (Exception e) {			
			manager.getTransaction().rollback();			
			throw new RuntimeException(e);
		}
		
		manager.getTransaction().commit();
		
		return result;
	}
	
	
}
