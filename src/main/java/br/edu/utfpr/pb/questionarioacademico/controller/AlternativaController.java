package br.edu.utfpr.pb.questionarioacademico.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.edu.utfpr.pb.questionarioacademico.model.Alternativa;
import br.edu.utfpr.pb.questionarioacademico.repository.AlternativaRepository;

@SuppressWarnings("serial")
@Controller
@Path("alternativas")
public class AlternativaController extends br.edu.utfpr.pb.questionarioacademico.controller.commons.Controller{

	private Result result;
	private AlternativaRepository repository;
	
	@Inject
	public AlternativaController(Result result, AlternativaRepository repository) {
		super(result);
		this.repository = repository;
		this.result = result;
	}
	
	/*CDI Constructor*/
	protected AlternativaController(){
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
	public void insert(Alternativa alternativa) {
		repository.insert(alternativa);
		result.nothing();
	}
	
	@Put
	@Path("/{alternativa.id}")
	@Consumes("application/json")
	public void update(Alternativa alternativa) {
		repository.update(alternativa);
		result.nothing();
	}

	@Delete
	@Path("/{alternativa.id}")
	public void delete(Alternativa alternativa) {
		repository.delete(alternativa);
		result.nothing();
	}
}
