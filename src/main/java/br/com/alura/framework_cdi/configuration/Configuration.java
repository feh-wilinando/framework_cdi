package br.com.alura.framework_cdi.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

public class Configuration {
	
	private Properties properties = new Properties();
	
	@Produces
	@Configurations
	@ApplicationScoped
	public Properties getProperties() {
		return properties;
	}

	@PostConstruct
	public void postConstruct(){
		
		try(InputStream inputStream = Configuration.class.getClassLoader().getResourceAsStream("/framework.properties")) {
			
			properties.load(inputStream);
			
		} catch ( IOException e) {
			throw new RuntimeException(e);
		}
	}
	
}
