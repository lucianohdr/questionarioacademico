package br.edu.utfpr.pb.questionarioacademico.seguranca.criptografia;

import java.security.KeyPair;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class GerarChave {
	
	private KeyPairGeneratorFactory factory; 
	private KeyPair keyPair;
	
	@Inject
	public GerarChave(KeyPairGeneratorFactory factory){
		this.factory = factory;
	}
	
	public void geraKeyPair(){
		this.keyPair = factory.create().generateKeyPair();
	}
	
	public KeyPair getKeyPair(){
		return this.keyPair;
	}
}