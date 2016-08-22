package br.com.alura.framework_cdi.interceptors;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Auditable
public class LifeCycleBean implements Serializable{


	private static final long serialVersionUID = -5609785844725029239L;
	
	@Inject
	private transient Logger logger;
	
	@AroundConstruct
	public Object aroundConstruct(InvocationContext context) throws Exception{
		
		logger.info("before Constructor");
		
		Object result = context.proceed();
		
		logger.info("after Constructor");
		
		return result;
		
	}
	
	@PostConstruct
	public void postConstruct(InvocationContext context) throws Exception{
		logger.info("before PostConstruct");
		
		context.proceed();
		
		logger.info("after PostConstruct");
	}
		
	@AroundInvoke
	public Object aroundInvoke(InvocationContext context) throws Exception{
		logger.info("before execute " + context.getMethod().getName() );
		
		Object result = context.proceed();
		
		logger.info("after execute " + context.getMethod().getName());
		
		return result;
	}
	
	@PreDestroy
	public void preDestroy(InvocationContext context) throws Exception{
		logger.info("before PreDestroy");
		
		context.proceed();
		
		logger.info("after PreDestroy");
		
	}
	
}
