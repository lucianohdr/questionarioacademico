package br.edu.utfpr.pb.questionarioacademico.seguranca.criptografia;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import javax.enterprise.context.Dependent;

@Dependent
public class KeyPairGeneratorFactory {

	private KeyPairGenerator generator; 
	
	public KeyPairGeneratorFactory(){
		this.generator = create();
	}
	
	public KeyPairGenerator create(){
		try {
			this.generator = KeyPairGenerator.getInstance("RSA");
			this.generator.initialize(1024);
			return this.generator;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
}