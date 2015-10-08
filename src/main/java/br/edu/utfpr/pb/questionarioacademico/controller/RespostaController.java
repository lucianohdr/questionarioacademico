package br.edu.utfpr.pb.questionarioacademico.controller;

import javax.inject.Inject;

import br.com.caelum.brutauth.auth.annotations.CustomBrutauthRules;
import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.edu.utfpr.pb.questionarioacademico.model.Resposta;
import br.edu.utfpr.pb.questionarioacademico.repository.RespostaRepositoty;
import br.edu.utfpr.pb.questionarioacademico.seguranca.regras.LoggedAccessRule;

@SuppressWarnings("serial")
@Controller
@Path("respostas")
@CustomBrutauthRules(LoggedAccessRule.class)
public class RespostaController extends br.edu.utfpr.pb.questionarioacademico.controller.commons.Controller{
	
	private RespostaRepositoty repository;
	private Result result;

	@Inject
	public RespostaController(Result result,
							  RespostaRepositoty repositoty) {
		super(result);
		this.result = result;
		this.repository = repositoty;
	}
	
	@Get
	@Path({"","/"})
	public void list() {
		serializer(repository.findAll()).serialize();
	}

	@Get
	@Path("/{start}/{limit}")
	public void list(Integer start, Integer limit) {
		serializer(repository.pagination(start, limit, null)).serialize();
	}

	@Get
	@Path("/{id}")
	public void find(Long id) {
		serializer(repository.find(id),true).serialize();
	}

	@Post
	@Path({"","/"})
	@Consumes("application/json")
	public void insert(Resposta resposta) {
		repository.insert(resposta);
		result.nothing();
	}
	
	@Put
	@Path("/{resposta.id}")
	@Consumes("application/json")
	public void update(Resposta resposta) {
		repository.update(resposta);
		result.nothing();
	}

	@Delete
	@Path("/{resposta.id}")
	public void delete(Resposta resposta) {
		repository.delete(resposta);
		result.nothing();
	}
}