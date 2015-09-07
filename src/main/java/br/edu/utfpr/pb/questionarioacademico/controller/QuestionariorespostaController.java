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
import br.edu.utfpr.pb.questionarioacademico.model.Questionarioresposta;
import br.edu.utfpr.pb.questionarioacademico.repository.QuestionariorespostaRepository;

@SuppressWarnings("serial")
@Controller
@Path("questionariorespostas")
public class QuestionariorespostaController extends br.edu.utfpr.pb.questionarioacademico.controller.commons.Controller{

	private QuestionariorespostaRepository repository;
	private Result result; 
	
	@Inject
	public QuestionariorespostaController(Result result,
										  QuestionariorespostaRepository repository) {
		super(result);
		this.result = result;
		this.repository = repository;
	}
	
	/*CDI construtor	*/
	public QuestionariorespostaController(){
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
	public void insert(Questionarioresposta questionarioresposta) {
		repository.insert(questionarioresposta);
		result.nothing();
	}
	
	@Put
	@Path("/{questionarioresposta.id}")
	@Consumes("application/json")
	public void update(Questionarioresposta questionarioresposta) {
		repository.update(questionarioresposta);
		result.nothing();
	}

	@Delete
	@Path("/{questionarioresposta.id}")
	public void delete(Questionarioresposta questionarioresposta) {
		repository.delete(questionarioresposta);
		result.nothing();
	}
	
	@Post
	@Path({"/finalizaAvaliacao"})
	@Consumes("application/json")
	public void finalizaAvaliacao(Questionarioresposta questionarioresposta) {
		
		//salva objeto com as respostas
		questionarioresposta = repository.insertReturn(questionarioresposta);
		
		//salvar usuario entres os alunos respondidos, ou entre os professores respondidos caso nao for um usuario "ALUNO"
		
		result.nothing();
	}
}