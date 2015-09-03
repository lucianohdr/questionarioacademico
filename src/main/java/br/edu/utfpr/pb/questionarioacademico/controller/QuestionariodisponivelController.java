package br.edu.utfpr.pb.questionarioacademico.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.edu.utfpr.pb.questionarioacademico.enums.questionario.Status;
import br.edu.utfpr.pb.questionarioacademico.model.Questionario;
import br.edu.utfpr.pb.questionarioacademico.model.Questionariodisponivel;
import br.edu.utfpr.pb.questionarioacademico.model.Usuario;
import br.edu.utfpr.pb.questionarioacademico.repository.QuestionariodisponivelRepository;
import br.edu.utfpr.pb.questionarioacademico.repository.UsuarioRepository;

@SuppressWarnings("serial")
@Controller
@Path("questionariodisponivels")
public class QuestionariodisponivelController extends br.edu.utfpr.pb.questionarioacademico.controller.commons.Controller{

	private Result result;
	private QuestionariodisponivelRepository repository;
	private UsuarioRepository usuarioRepository;
	
	@Inject
	public QuestionariodisponivelController(Result result,
											QuestionariodisponivelRepository repository,
											UsuarioRepository usuarioRepository) {
		super(result);
		this.result = result;
		this.repository = repository;
		this.usuarioRepository = usuarioRepository;
	}

	/*CDI only*/
	public QuestionariodisponivelController() {
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
		serializer(repository.find(id),true)
		.exclude("questionario.questionariodisponivels")
		.exclude("questionario.perguntas.alternativas.pergunta")
		.serialize();
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
	
	@Get
	@Path("/porIdquestionario/{idquestionario}")
	public void porIdquestionario(Long idquestionario) {
		List<Questionariodisponivel> questionariodisponivels = 
				new ArrayList<Questionariodisponivel>(repository.porIdquestionario(idquestionario));
		
		serializer(questionariodisponivels)
		.exclude("questionario.questionariodisponivels.questionario")
		.serialize();
	}
	
	@Post
	@Path("/porUsuario")
	@Consumes("application/json")
	public void porUsuario(Usuario usuario) {
		usuario = usuarioRepository.find(usuario.getId());
		
		List<Questionariodisponivel> questionariodisponivels = repository.porUsuarioEporStatus(usuario, Status.EMCURSO);
		serializer(questionariodisponivels)
		.exclude("questionario.questionariodisponivels")
		.exclude("questionario.perguntas.alternativas.pergunta")
		.serialize();
	}
	
	/*@Post
	@Path("/responder")
	@Consumes("application/json")
	public void responder(Questionario questionario, Usuario usuario) {
		usuario = usuarioRepository.find(usuario.getId());
		
		questionario = repository.responder(questionario, usuario);
		
		//filtrar disciplinas por usuario e questionario
		
		serializer(questionario, true)
		.exclude("perguntas.alternativas.pergunta")
		.exclude("questionariodisponivels.questionario")
		.serialize();
	}*/
}