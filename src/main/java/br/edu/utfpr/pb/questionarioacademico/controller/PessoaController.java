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
import br.edu.utfpr.pb.questionarioacademico.model.Pessoa;
import br.edu.utfpr.pb.questionarioacademico.repository.PessoaRepository;
import br.edu.utfpr.pb.questionarioacademico.seguranca.regras.LoggedAccessRule;

@SuppressWarnings("serial")
@Controller
@Path("pessoas")
@CustomBrutauthRules(LoggedAccessRule.class)
public class PessoaController extends br.edu.utfpr.pb.questionarioacademico.controller.commons.Controller{

	private Result result;
	private PessoaRepository repository;
	
	@Inject
	public PessoaController(Result result, PessoaRepository repository) {
		super(result);
		this.result = result;
		this.repository = repository;
	}

	/*CDI Only*/
	protected PessoaController() {
		this(null, null);
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
	public void insert(Pessoa pessoa) {
		repository.insert(pessoa);
		result.nothing();
	}
	
	@Put
	@Path("/{pessoa.id}")
	@Consumes("application/json")
	public void update(Pessoa pessoa) {
		repository.update(pessoa);
		result.nothing();
	}

	@Delete
	@Path("/{pessoa.id}")
	public void delete(Pessoa pessoa) {
		repository.delete(pessoa);
		result.nothing();
	}
}