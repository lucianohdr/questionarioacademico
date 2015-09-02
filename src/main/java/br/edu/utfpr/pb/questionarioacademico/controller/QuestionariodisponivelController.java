package br.edu.utfpr.pb.questionarioacademico.controller;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.edu.utfpr.pb.questionarioacademico.model.Questionariodisponivel;
import br.edu.utfpr.pb.questionarioacademico.repository.QuestionariodisponivelRepository;

@SuppressWarnings("serial")
@Controller
@Path("questionariodisponivels")
public class QuestionariodisponivelController extends br.edu.utfpr.pb.questionarioacademico.controller.commons.Controller{

	private Result result;
	private QuestionariodisponivelRepository repository;
	
	public QuestionariodisponivelController(Result result,
											QuestionariodisponivelRepository repository) {
		super(result);
		this.result = result;
		this.repository = repository;
	}

	/*CDI only*/
	public QuestionariodisponivelController() {
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
	public void insert(Questionariodisponivel questionariodisponivel) {
		repository.insert(questionariodisponivel);
		result.nothing();
	}
	
	@Put
	@Path("/{questionariodisponivel.id}")
	@Consumes("application/json")
	public void update(Questionariodisponivel questionariodisponivel) {
		repository.update(questionariodisponivel);
		result.nothing();
	}

	@Delete
	@Path("/{questionariodisponivel.id}")
	public void delete(Questionariodisponivel questionariodisponivel) {
		repository.delete(questionariodisponivel);
		result.nothing();
	}
}