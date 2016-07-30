package br.com.alura.framework_cdi.interceptors.tx;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Interceptor
@Trasactional
public class ManagerTransaction implements Serializable{

	private static final long serialVersionUID = -8923127888496650946L;

	@Inject
	private EntityManager manager;
	
	@Inject
	private transient Logger logger;
	
	@AroundInvoke
	public Object executeWithTransaction(InvocationContext context){
			
		logger.info("Begin Transaction");
		
		manager.getTransaction().begin();
		
		Object result;
		
		try {
			result = context.proceed();
		} catch (Exception e) {
			
			logger.info("Rollback Transaction");
			
			manager.getTransaction().rollback();			
			throw new RuntimeException(e);
		}
		
		logger.info("Commit Transaction");
		manager.getTransaction().commit();
		
		return result;
	}
	
	
}
