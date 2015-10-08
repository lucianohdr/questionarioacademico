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
import br.edu.utfpr.pb.questionarioacademico.model.Periodo;
import br.edu.utfpr.pb.questionarioacademico.repository.PeriodoRepository;
import br.edu.utfpr.pb.questionarioacademico.seguranca.regras.LoggedAccessRule;

@SuppressWarnings("serial")
@Controller
@Path("periodos")
@CustomBrutauthRules(LoggedAccessRule.class)
public class PeriodoController extends br.edu.utfpr.pb.questionarioacademico.controller.commons.Controller{

	private Result result;
	private PeriodoRepository repository;
	
	@Inject
	public PeriodoController(Result result, PeriodoRepository repository) {
		super(result);
		this.result = result;
		this.repository = repository;
	}

	/*CDI only*/
	protected PeriodoController(){
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
	public void insert(Periodo periodo) {
		repository.insert(periodo);
		result.nothing();
	}
	
	@Put
	@Path("/{periodo.id}")
	@Consumes("application/json")
	public void update(Periodo periodo) {
		repository.update(periodo);
		result.nothing();
	}

	@Delete
	@Path("/{periodo.id}")
	public void delete(Periodo periodo) {
		repository.delete(periodo);
		result.nothing();
	}
}
