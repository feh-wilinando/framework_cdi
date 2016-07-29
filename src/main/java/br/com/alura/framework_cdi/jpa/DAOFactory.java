package br.com.alura.framework_cdi.jpa;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Vetoed;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Vetoed
public class DAOFactory {

	@Inject
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	@Produces
	@RequestScoped
	public <M, P> DAO<M,P> factory(InjectionPoint ip){
		ParameterizedType type = (ParameterizedType) ip.getType();
		Class<M> modelClass = (Class<M>) type.getActualTypeArguments()[0];
		
		return new DAO<>(modelClass, manager);
	}
	
}
