package br.edu.utfpr.pb.questionarioacademico.business;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.Perfil;
import br.edu.utfpr.pb.questionarioacademico.model.Tela;
import br.edu.utfpr.pb.questionarioacademico.model.Usuario;
import br.edu.utfpr.pb.questionarioacademico.repository.UsuarioRepository;
import br.edu.utfpr.pb.questionarioacademico.seguranca.Hasher;
import br.edu.utfpr.pb.questionarioacademico.seguranca.criptografia.KeyPairGenerator;

@Stateless
public class UsuarioBusiness extends RepositoryImpl<Usuario, Long> implements UsuarioRepository{

	private final KeyPairGenerator gerarChave;
	
	@Inject
	public UsuarioBusiness(KeyPairGenerator gerarChave){
		this.gerarChave = gerarChave; 
	}
	
	@Override
	public Usuario getByUsernameAndPassword(String login, String senha) {

		/*
		 * Mais uma vez, esse código não toca no sql nativo, por isso pode ser chamado de Repository pattern
		 */
		StringBuilder queryString = new StringBuilder("from Usuario where login = ? and senha = ?");

		Query query = entityManager.createQuery(queryString.toString());
		
		query.setParameter(1, login);
		query.setParameter(2, senha);
		
		List<Usuario> list = query.getResultList();
		
		if (!list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public boolean loginDisponivel(String login) {
		
		StringBuilder queryString = new StringBuilder("from Usuario where login = :login");
		Query query = entityManager.createQuery(queryString.toString());
		
		query.setParameter("login", login);
		
		if(query.getResultList().isEmpty()){
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void insert(Usuario usuario) {
		usuario.setSenha(Hasher.get(usuario.getSenha()));
		
		//gerando chaves
		gerarChave.geraKeyPair();
		
		//armazenando par de chaves
		KeyPair keyPair = gerarChave.getKeyPair();
		
		//setando em usuario para ser salvos
		usuario.setPrivatekey(keyPair.getPrivate().getEncoded());
		usuario.setPublickey(keyPair.getPublic().getEncoded());
		
		super.insert(usuario);
	}
	
	@Override
	public Usuario insertReturn(Usuario usuario) {
		usuario.setSenha(Hasher.get(usuario.getSenha()));
		
		//gerando chaves
		gerarChave.geraKeyPair();
		
		//armazenando par de chaves
		KeyPair keyPair = gerarChave.getKeyPair();
		
		//setando em usuario para ser salvos
		usuario.setPrivatekey(keyPair.getPrivate().getEncoded());
		usuario.setPublickey(keyPair.getPublic().getEncoded());
		
		return super.insertReturn(usuario);
	}

	@Override
	public List<String> getRoles(Usuario usuario) {
		List<Perfil> perfis = new ArrayList<Perfil>(usuario.getPerfis());
		List<String> retorno = new ArrayList<String>();
		
		for(Perfil perfil: perfis){
			retorno.add(perfil.getNome().toUpperCase());
		}
		return retorno;
	}

	@Override
	public List<Tela> getTelas(Usuario usuario) {
		List<Perfil> perfis = new ArrayList<Perfil>(usuario.getPerfis());
		List<Tela> retorno = new ArrayList<Tela>();
		for(Perfil perfil: perfis){
			List<Tela> telas = new ArrayList<Tela>(perfil.getTelas());
			for(Tela tela: telas){
				//TODO: testar para ver se nao adicionar mais de uma tela por usuario
				if(!retorno.contains(tela)){
					retorno.add(tela);
				}
			}
		}
		
		return retorno;
	}
}