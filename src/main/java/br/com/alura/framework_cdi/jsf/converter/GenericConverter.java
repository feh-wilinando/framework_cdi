package br.com.alura.framework_cdi.jsf.converter;

import java.util.StringTokenizer;

import javax.enterprise.inject.Alternative;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnitUtil;

@Alternative
@Named
public class GenericConverter implements Converter{	

	@Inject
	private EntityManager manager;
	
	@Inject
	private IdConverter idConverter;
		
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		StringTokenizer tokenizer = new StringTokenizer(value,":");
		String className = tokenizer.nextToken();
		String idEntity = tokenizer.nextToken();
		
		try {
			Class<?> clazz = Class.forName(className);			
			Object primaryKey = idConverter.getAsObject(context, component, idEntity);
			
			return manager.find(clazz, primaryKey);
		} catch (ClassNotFoundException e) {		
			throw new RuntimeException(e);
		}
		
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		String className = value.getClass().getName();
		
		Object idEntity = getIdFromEntity(value);
		String idEntityString = idConverter.getAsString(context, component, idEntity);
		
		
		return className + ":" + idEntityString;
	}
	
	
	private Object getIdFromEntity(Object entity){
		
		PersistenceUnitUtil jpaUtil = manager
										.getEntityManagerFactory()
										.getPersistenceUnitUtil();
		
		return jpaUtil.getIdentifier(entity);
		
	}


}
