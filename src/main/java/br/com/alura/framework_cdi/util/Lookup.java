package br.com.alura.framework_cdi.util;

import java.util.Set;

import javax.enterprise.inject.Vetoed;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;

@Vetoed
public class Lookup<T> {

	private Class<T> clazz;
	private BeanManager beanManager = CDI.current().getBeanManager();
	
	public Lookup(Class<T> clazz) {
		this.clazz = clazz;				
	}
	
	@SuppressWarnings("unchecked")
	public T getBean(){
		
		Set<Bean<?>> beans = beanManager.getBeans(clazz);
		Bean<T> bean = (Bean<T>) beanManager.resolve(beans);		
		T reference = (T) beanManager.getReference(bean, bean.getBeanClass(), beanManager.createCreationalContext(bean));
		
		return reference;
	}
	
	
}
