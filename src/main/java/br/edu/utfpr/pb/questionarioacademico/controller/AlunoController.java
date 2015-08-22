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
import br.edu.utfpr.pb.questionarioacademico.model.Aluno;
import br.edu.utfpr.pb.questionarioacademico.model.Perfil;
import br.edu.utfpr.pb.questionarioacademico.model.Usuario;
import br.edu.utfpr.pb.questionarioacademico.repository.AlunoRepositoty;
import br.edu.utfpr.pb.questionarioacademico.repository.UsuarioRepository;
import br.edu.utfpr.pb.questionarioacademico.seguranca.Hasher;

@SuppressWarnings("serial")
@Controller
@Path("alunos")
public class AlunoController extends br.edu.utfpr.pb.questionarioacademico.controller.commons.Controller{

	private Result result;
	private AlunoRepositoty repository;
	private UsuarioRepository usuarioRepository;
	
	@Inject
	public AlunoController(Result result, AlunoRepositoty repository, UsuarioRepository usuarioRepository) {
		super(result);
		this.result = result;
		this.repository = repository;
		this.usuarioRepository = usuarioRepository;
	}

	/*CDI construtor*/
	protected AlunoController(){
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
	public void insert(Aluno aluno) {
		
		//setando o perfil aluno em usuario
		aluno.getPessoa().getUsuario().getPerfis().add(new Perfil("ALUNO"));
		
		//fazendo criptografia da senha
		aluno.getPessoa().getUsuario().setSenha(Hasher.get(aluno.getPessoa().getUsuario().getSenha()));
		
		repository.insert(aluno);
		result.nothing();
	}
	
	@Put
	@Path("/{aluno.id}")
	@Consumes("application/json")
	public void update(Aluno aluno) {
		repository.update(aluno);
		result.nothing();
	}

	@Delete
	@Path("/{aluno.id}")
	public void delete(Aluno aluno) {
		repository.delete(aluno);
		result.nothing();
	}
}