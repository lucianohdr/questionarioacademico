package br.edu.utfpr.pb.questionarioacademico.seguranca;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.edu.utfpr.pb.questionarioacademico.model.Perfil;

/**
 * 
 * @author trgp
 *
 * Anotação criada para poder criar acesso restrito a recursos restful específicos
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface SecRole {

	String[] roles();
	
}