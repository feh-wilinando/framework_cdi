package br.com.alura.framework_cdi.jsf.converter;

public interface Convertible {

	Object asObject(String value);

	String asString(Object value);

}
