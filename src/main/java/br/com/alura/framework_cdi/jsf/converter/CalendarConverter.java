package br.com.alura.framework_cdi.jsf.converter;

import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Alternative;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.DateTimeConverter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.alura.framework_cdi.configuration.Configurations;

@Named
@Alternative
public class CalendarConverter implements Converter{
	
	@Inject @Configurations
	private Properties configurations;
	
	private DateTimeConverter originalConverter = new DateTimeConverter();	

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Date inputTime = (Date) originalConverter.getAsObject(context, component, value);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(inputTime);
		
		return calendar;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		Calendar inputCalendar =  (Calendar) value;
		Date time = inputCalendar.getTime();
						
		return originalConverter.getAsString(context, component, time);
	}
	
	
	@PostConstruct
	private void loadTimePattern(){
		String pattern = configurations.getProperty("framework.jsf.converter.DefaultDateTimePattern");
		originalConverter.setPattern(pattern);
	}

}
