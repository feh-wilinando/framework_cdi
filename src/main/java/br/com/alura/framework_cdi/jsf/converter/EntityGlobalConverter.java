package br.com.alura.framework_cdi.jsf.converter;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Id;

@Alternative
public class EntityGlobalConverter<T> implements Converter {
	
	@Inject
	private EntityManager manager;
	
	private Class<T> classe;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		return manager.find(classe, Long.parseLong(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
				
		return getValueForFieldId(value);
	}

	public String getValueForFieldId(Object value) {
		Field field = Arrays.stream(value.getClass().getDeclaredFields())
					.filter(attibuto -> attibuto.isAnnotationPresent(Id.class))
					.findFirst().get();
		
		field.setAccessible(true);
		
		String id;
		
		try {
			id = field.get(value).toString();
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
				
		return id;
	}

	
	@PostConstruct
	@SuppressWarnings("unchecked")
	public void postConstruct(InjectionPoint injectionPoint){
		ParameterizedType type = (ParameterizedType) injectionPoint.getType();
		this.classe = (Class<T>) type.getActualTypeArguments()[0];
	}
	
}
