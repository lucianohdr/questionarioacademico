package br.edu.utfpr.pb.questionarioacademico.seguranca;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.hibernate4.encryptor.HibernatePBEEncryptorRegistry;

import br.com.caelum.vraptor.environment.Property;
import br.com.caelum.vraptor.events.VRaptorInitialized;

@ApplicationScoped
public class InicializadorEncryptor {
	
	private String password;
	
	private StandardPBEStringEncryptor defaultEncryptor;
	
	@Inject 
	public InicializadorEncryptor(@Property(value="jasypt.crypt.password") String password) {
		this.password = password;
	}
	
	public void registerEncryptor(){
		configureEncryptor();
		
		HibernatePBEEncryptorRegistry registry = HibernatePBEEncryptorRegistry.getInstance();
		registry.registerPBEStringEncryptor("HibernateStringEncryptor", defaultEncryptor);
	}
	
	
	public void configureEncryptor(){
		this.defaultEncryptor = new StandardPBEStringEncryptor();
		this.defaultEncryptor.setPassword(password);
	}
	
	@Produces
	public StandardPBEStringEncryptor getDefaultStringEncryptor(){
		if(null == defaultEncryptor){
			registerEncryptor();
		}
		return this.defaultEncryptor;
	}
	
	public void whenAppInitialize(@Observes VRaptorInitialized event){
		registerEncryptor();
	}
}