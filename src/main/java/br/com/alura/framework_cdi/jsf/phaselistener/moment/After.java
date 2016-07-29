package br.com.alura.framework_cdi.jsf.phaselistener.moment;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;

@Qualifier
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface After {}
