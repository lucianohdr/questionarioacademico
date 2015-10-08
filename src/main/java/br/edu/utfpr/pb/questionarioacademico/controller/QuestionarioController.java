package br.edu.utfpr.pb.questionarioacademico.controller;

import java.util.ArrayList;
import java.util.List;

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
import br.edu.utfpr.pb.questionarioacademico.model.Pergunta;
import br.edu.utfpr.pb.questionarioacademico.model.Questionario;
import br.edu.utfpr.pb.questionarioacademico.repository.QuestionarioRepository;
import br.edu.utfpr.pb.questionarioacademico.seguranca.regras.AdminAccessRule;
import br.edu.utfpr.pb.questionarioacademico.seguranca.regras.LoggedAccessRule;

@SuppressWarnings("serial")
@Controller
@Path("questionarios")
@CustomBrutauthRules({LoggedAccessRule.class, AdminAccessRule.class})
public class QuestionarioController extends br.edu.utfpr.pb.questionarioacademico.controller.commons.Controller{

	private Result result;
	private QuestionarioRepository repository;
	
	@Inject
	public QuestionarioController(Result result, 
								  QuestionarioRepository repository) {
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
		List<Questionario> list = repository.pagination(start, limit, null);
		serializer(list)
			.serialize();
	}

	@Get
	@Path("/{id}")
	public void find(Long id) {
		Questionario questionario = repository.find(id);
		questionario.getQuestionariodisponivels();
		serializer(questionario, true)
			.serialize();
	}

	@Post
	@Path({"","/"})
	@Consumes("application/json")
	public void insert(Questionario questionario) {
		serializer(repository.insertReturn(questionario))
			.serialize(); 
	}
	
	@Put
	@Path("/{questionario.id}")
	@Consumes("application/json")
	public void update(Questionario questionario) {
		repository.update(questionario);
		result.nothing();
	}
	
	@Post
	@Path("/{questionario.id}")
	@Consumes("application/json")
	public void updateReturn(Questionario questionario) {
		questionario = repository.update(questionario);
		serializer(questionario).serialize();
	}

	@Delete
	@Path("/{questionario.id}")
	public void delete(Questionario questionario) {
		repository.delete(questionario);
		result.nothing();
	}
	
	@Post
	@Path("/addPergunta")
	@Consumes("application/json")
	public void addPergunta(Questionario questionario, Pergunta pergunta){
		
		questionario.addPergunta(pergunta);
		repository.update(questionario);
		
		serializer(questionario)
			.serialize();
	}
	
	@Post
	@Path("/perguntas")
	@Consumes("application/json")
	public void perguntas(Questionario questionario){
		questionario = repository.find(questionario.getId());
		List<Pergunta> perguntas = new ArrayList<Pergunta>(questionario.getPerguntas());
		
		serializer(perguntas)
			.exclude("alternativas.pergunta")
			.serialize();
	}
	
	@Post
	@Path("/rmPergunta")
	@Consumes("application/json")
	public void rmPergunta(Questionario questionario, Pergunta pergunta){
		
		questionario = repository.find(questionario.getId());
		questionario.rmPergunta(pergunta);
		repository.update(questionario);
		
		result.nothing();
	}
	
	@Post
	@Path("/liberarQuestionario")
	@Consumes("application/json")
	public void liberarQuestionario(Questionario questionario) {
		questionario = repository.liberarQuestionario(questionario);
		serializer(questionario)
		.serialize();
	}
	
	@Override
	protected String[] excludeProps() {
		return new String[]{
				"perguntas.alternativas.pergunta",
				"questionariodisponivels.questionario",
				"questionariodisponivels.questionariorespostas.questionariodisponivel",
				"questionariodisponivels.questionariorespostas.questionariodisponivel.questionariorespostas"
		};
	}
}