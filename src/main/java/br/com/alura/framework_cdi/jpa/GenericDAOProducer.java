package br.com.alura.framework_cdi.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.persistence.EntityManager;


@SuppressWarnings("unchecked")
public class GenericDAOProducer implements Serializable{

	private static final long serialVersionUID = -309952515118342501L;

	@Inject
	private EntityManager manager; 
		
	@Produces	
	public <M, P> DAO<M,P> factory(InjectionPoint ip){
		ParameterizedType type = (ParameterizedType) ip.getType();
		Class<M> modelClass = (Class<M>) type.getActualTypeArguments()[0];
					
		
		
		return new DAO<>(modelClass, manager);
	}

	
	
	
	
	
	
}
