package br.com.alura.framework_cdi.jpa;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Alternative
@ApplicationScoped
public class EntityManagerProductor {
				
	private final String perSistenceUnitKey = "CDI_PERSISTENCE_UNIT";
	private EntityManagerFactory factory;
	
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
		
		String persistenceUnitName = System.getenv().get(perSistenceUnitKey);
		
		factory = Persistence.createEntityManagerFactory(persistenceUnitName);
	}

	@PreDestroy
	public void preDestroy(){
		if (factory.isOpen()) {
			factory.close();
		}
	}
	
}
