package br.com.alura.framework_cdi.jpa;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Vetoed;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

@Vetoed
public class DAO<M, P> implements Serializable{

	private static final long serialVersionUID = -6732949028086409600L;
	private EntityManager manager;
	private Class<M> modelClass;

	public DAO(Class<M> modelClass, EntityManager manager){
		this.modelClass = modelClass;
		this.manager = manager;		
	}
	
	public M findById(P id){
		return manager.find(modelClass, id);
	}
	
	public void save(M modelObject){
		manager.persist(modelObject);
	}
	
	public void update(M modelObject){
		manager.merge(modelObject);
	}
	
	public void remove(M modelObject){
		manager.remove(manager.merge(modelObject));
	}
	
	public List<M> list(){
		CriteriaQuery<M> query = manager.getCriteriaBuilder().createQuery(modelClass);
		query.select(query.from(modelClass));
		
		return manager.createQuery(query).getResultList();		
	}
	
}
