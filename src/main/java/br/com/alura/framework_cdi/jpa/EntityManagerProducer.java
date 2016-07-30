package br.com.alura.framework_cdi.jpa;

import java.io.Serializable;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.framework_cdi.configuration.Configurations;


@ApplicationScoped
public class EntityManagerProducer implements Serializable{
				

	private static final long serialVersionUID = -448805665218824089L;

	private EntityManagerFactory factory;
	
	@Inject @Configurations
	private Properties configurations;
	
	@Produces
	@RequestScoped
	public EntityManager getEntityManger(){
		return factory.createEntityManager();
	}
	
	
	public void closeEntityManager(@Disposes EntityManager manager){
		if (manager.isOpen()) {
			manager.close();
		}
	}
	
	
	@PostConstruct
	public void postConstruct(){
		
		String persistenceUnitName = configurations.getProperty("framework.persistence.unit");
		
		factory = Persistence.createEntityManagerFactory(persistenceUnitName);
	}

	@PreDestroy
	public void preDestroy(){
		if (factory.isOpen()) {
			factory.close();
		}
	}
	
}
