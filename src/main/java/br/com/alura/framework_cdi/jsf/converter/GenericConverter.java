package br.com.alura.framework_cdi.jsf.converter;

import java.lang.reflect.ParameterizedType;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Alternative
public class GenericConverter<T extends Convertible> implements Converter{	
		
	private T genericObject;
		
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return genericObject.asObject(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return genericObject.asString(value);
	}
	
	@PostConstruct	
	public void postConstruct(InjectionPoint injectionPoint){
		ParameterizedType type = (ParameterizedType) injectionPoint.getType();
		
		@SuppressWarnings("unchecked")
		Class<T> classe = (Class<T>) type.getActualTypeArguments()[0];
		
		try {
			genericObject = classe.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		
	}

}
