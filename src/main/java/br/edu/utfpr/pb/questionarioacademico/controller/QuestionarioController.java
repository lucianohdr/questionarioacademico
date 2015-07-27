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
import br.edu.utfpr.pb.questionarioacademico.model.Questionario;
import br.edu.utfpr.pb.questionarioacademico.repository.QuestionarioRepository;

@SuppressWarnings("serial")
@Controller
@Path("questionarios")
public class QuestionarioController extends br.edu.utfpr.pb.questionarioacademico.controller.commons.Controller{

	private Result result;
	private QuestionarioRepository repository;
	
	@Inject
	public QuestionarioController(Result result, QuestionarioRepository repository) {
		super(result);
		this.repository = repository;
		this.result = result;
	}
	
	/*CDI only*/
	protected QuestionarioController(){
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
	public void insert(Questionario questionario) {
		repository.insert(questionario);
		result.nothing();
	}
	
	@Put
	@Path("/{questionario.id}")
	@Consumes("application/json")
	public void update(Questionario questionario) {
		repository.update(questionario);
		result.nothing();
	}

	@Delete
	@Path("/{questionario.id}")
	public void delete(Questionario questionario) {
		repository.delete(questionario);
		result.nothing();
	}
}