package br.com.alura.framework_cdi.logger;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class LoggerProductor implements Serializable{

	private static final long serialVersionUID = 2081539438414403939L;

	@Produces
	public Logger getLogger(InjectionPoint ip){
		String name = "[Framework-CDI]" + ip.getMember().getDeclaringClass().getName();
		
		return Logger.getLogger(name);
	}
	
}
