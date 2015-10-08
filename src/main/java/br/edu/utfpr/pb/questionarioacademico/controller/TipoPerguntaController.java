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
import br.edu.utfpr.pb.questionarioacademico.model.Tipopergunta;
import br.edu.utfpr.pb.questionarioacademico.repository.TipoPerguntaRepository;
import br.edu.utfpr.pb.questionarioacademico.seguranca.regras.LoggedAccessRule;

@SuppressWarnings("serial")
@Controller
@Path("tipoperguntas")
@CustomBrutauthRules(LoggedAccessRule.class)
public class TipoPerguntaController extends br.edu.utfpr.pb.questionarioacademico.controller.commons.Controller{

	private Result result;
	private TipoPerguntaRepository repository;
	
	@Inject
	public TipoPerguntaController(Result result, TipoPerguntaRepository repository) {
		super(result);
		this.repository = repository;
		this.result = result;
	}

	/*CDI Only*/
	protected TipoPerguntaController(){
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
	public void insert(Tipopergunta tipopergunta) {
		repository.insert(tipopergunta);
		result.nothing();
	}
	
	@Put
	@Path("/{tipopergunta.id}")
	@Consumes("application/json")
	public void update(Tipopergunta tipopergunta) {
		repository.update(tipopergunta);
		result.nothing();
	}

	@Delete
	@Path("/{tipopergunta.id}")
	public void delete(Tipopergunta tipopergunta) {
		repository.delete(tipopergunta);
		result.nothing();
	}
}