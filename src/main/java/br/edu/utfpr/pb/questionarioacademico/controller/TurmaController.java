package br.edu.utfpr.pb.questionarioacademico.controller;

import javax.inject.Inject;

import br.com.caelum.brutauth.auth.annotations.CustomBrutauthRules;
import br.com.caelum.brutauth.auth.annotations.Public;
import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.edu.utfpr.pb.questionarioacademico.model.Turma;
import br.edu.utfpr.pb.questionarioacademico.repository.TurmaRepository;
import br.edu.utfpr.pb.questionarioacademico.seguranca.regras.LoggedAccessRule;

@SuppressWarnings("serial")
@Controller
@Path("turmas")
@CustomBrutauthRules(LoggedAccessRule.class)
public class TurmaController extends br.edu.utfpr.pb.questionarioacademico.controller.commons.Controller{

	private Result result;
	private TurmaRepository repository;
	
	@Inject
	public TurmaController(Result result, TurmaRepository repository) {
		super(result);
		this.repository = repository;
		this.result = result;
	}
	/*CDI only*/
	protected TurmaController() {
		this(null, null);
	}

	@Public
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
	public void insert(Turma turma) {
		repository.insert(turma);
		result.nothing();
	}
	
	@Put
	@Path("/{turma.id}")
	@Consumes("application/json")
	public void update(Turma turma) {
		repository.update(turma);
		result.nothing();
	}

	@Delete
	@Path("/{turma.id}")
	public void delete(Turma turma) {
		repository.delete(turma);
		result.nothing();
	}
}
