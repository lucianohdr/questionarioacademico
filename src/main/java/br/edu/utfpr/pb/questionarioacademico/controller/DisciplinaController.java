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
import br.edu.utfpr.pb.questionarioacademico.model.Disciplina;
import br.edu.utfpr.pb.questionarioacademico.repository.DisciplinaRepository;

@SuppressWarnings("serial")
@Controller
@Path("disciplinas")
public class DisciplinaController extends br.edu.utfpr.pb.questionarioacademico.controller.commons.Controller{

	private Result result;
	private DisciplinaRepository repository;
	
	@Inject
	public DisciplinaController(Result result, DisciplinaRepository repository) {
		super(result);
		this.result = result;
		this.repository = repository;
	}

	/*CDI only*/
	protected DisciplinaController(){
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
	public void insert(Disciplina disciplina) {
		repository.insert(disciplina);
		result.nothing();
	}
	
	@Put
	@Path("/{disciplina.id}")
	@Consumes("application/json")
	public void update(Disciplina disciplina) {
		repository.update(disciplina);
		result.nothing();
	}

	@Delete
	@Path("/{disciplina.id}")
	public void delete(Disciplina disciplina) {
		repository.delete(disciplina);
		result.nothing();
	}
}
