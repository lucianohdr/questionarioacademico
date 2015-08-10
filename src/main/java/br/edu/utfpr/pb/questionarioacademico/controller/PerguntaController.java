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
import br.edu.utfpr.pb.questionarioacademico.model.Pergunta;
import br.edu.utfpr.pb.questionarioacademico.model.Questionario;
import br.edu.utfpr.pb.questionarioacademico.repository.PerguntaRepository;
import br.edu.utfpr.pb.questionarioacademico.repository.QuestionarioRepository;

@SuppressWarnings("serial")
@Controller
@Path("perguntas")
public class PerguntaController extends br.edu.utfpr.pb.questionarioacademico.controller.commons.Controller{

	private Result result;
	private PerguntaRepository repository;
	private QuestionarioRepository questionarioRepository;
	
	@Inject
	public PerguntaController(Result result, 
							  PerguntaRepository repository, 
							  QuestionarioRepository questionarioRepository) {
		super(result);
		this.repository = repository;
		this.questionarioRepository = questionarioRepository;
		this.result = result;
	}

	/*CDI Only*/
	protected PerguntaController(){
		this(null, null, null);
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
	public void insert(Pergunta pergunta) {
		repository.insertReturn(pergunta);
		serializer(repository.insertReturn(pergunta)).serialize();
	}
	
	@Put
	@Path("/{pergunta.id}")
	@Consumes("application/json")
	public void update(Pergunta pergunta) {
		repository.update(pergunta);
		result.nothing();
	}

	@Delete
	@Path("/{pergunta.id}")
	public void delete(Pergunta pergunta) {
		repository.delete(pergunta);
		result.nothing();
	}
}