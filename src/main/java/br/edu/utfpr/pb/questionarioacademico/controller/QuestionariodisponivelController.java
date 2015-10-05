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
import br.edu.utfpr.pb.questionarioacademico.model.Perfil;
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
		Questionariodisponivel questionariodisponivel = repository.find(id);
		
		serializer(questionariodisponivel, true)
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
	@Path("/porIdquestionario/{idquestionario}")/*ainda não usado*/
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
		.serialize();
	}
	
	@Post
	@Path("/respondidos")
	@Consumes("application/json")
	public void respondidos(Usuario usuario){
		usuario = usuarioRepository.find(usuario.getId());
		
		List<Questionariodisponivel> questionariodisponivels = repository.respondidosPorUsuario(usuario);
		serializer(questionariodisponivels)
		.serialize();
	}
	
	@Post
	@Path({"/respostasPorUsuarioEPorPerfil"})
	@Consumes("application/json")
	public void respostasPorUsuarioEPorPerfil(Perfil perfil) {
		
		List<Questionariodisponivel> list = repository.porUsuarioEPorPerfil(perfil);
		
		serializer(list)
		.serialize();
	}
	
	@Override
	protected String[] excludeProps() {
		return new String[]{
				"questionariorespostas.questionariodisponivel.questionariorespostas",
				"questionario.questionariodisponivels",
				"questionario.perguntas.alternativas.pergunta",
				"usuariosRespondidos"
		};
	}
}