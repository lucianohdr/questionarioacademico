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
import br.edu.utfpr.pb.questionarioacademico.model.Professor;
import br.edu.utfpr.pb.questionarioacademico.repository.ProfessorRepository;

@SuppressWarnings("serial")
@Controller
@Path("professors")
public class ProfessorController extends br.edu.utfpr.pb.questionarioacademico.controller.commons.Controller{

	private Result result;
	private ProfessorRepository repository;
	
	@Inject
	public ProfessorController(Result result, ProfessorRepository repository) {
		super(result);
		this.result = result;
		this.repository = repository;
	}
	
	/*CDI construtor*/
	protected ProfessorController(){
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
	public void insert(Professor professor) {
		repository.insert(professor);
		result.nothing();
	}
	
	@Put
	@Path("/{professor.id}")
	@Consumes("application/json")
	public void update(Professor professor) {
		repository.update(professor);
		result.nothing();
	}

	@Delete
	@Path("/{professor.id}")
	public void delete(Professor professor) {
		repository.delete(professor);
		result.nothing();
	}
}