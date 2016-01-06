package br.edu.utfpr.pb.questionarioacademico.controller;

import javax.inject.Inject;

import br.com.caelum.brutauth.auth.annotations.CustomBrutauthRules;
import br.com.caelum.brutauth.auth.annotations.Public;
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
import br.edu.utfpr.pb.questionarioacademico.seguranca.regras.LoggedAccessRule;

@SuppressWarnings("serial")
@Controller
@CustomBrutauthRules({LoggedAccessRule.class})
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
	@Public
	public void insert(Aluno aluno) {
		
		//setando o perfil aluno em usuario
		aluno.getPessoa().getUsuario().getPerfis().add(new Perfil("ALUNO"));
		
		aluno.getPessoa()
		.setUsuario(usuarioRepository.insertReturn(aluno.getPessoa().getUsuario()));
		
		repository.insertReturn(aluno);
		result.nothing();
	}
	
	@Put
	@Path("/{aluno.id}")
	@Consumes("application/json")
	public void update(Aluno aluno) {
		
		Aluno alunoAux = repository.find(aluno.getId());
		//setando novamente a senha ainda presente no banco
		
		aluno.getPessoa().getUsuario().setSenha(alunoAux.getPessoa().getUsuario().getSenha());
		repository.update(aluno);
		result.nothing();
	}

	@Delete
	@Path("/{aluno.id}")
	public void delete(Aluno aluno) {
		repository.delete(aluno);
		result.nothing();
	}
	
	@Post
	@Path("/alunoPorUsuario")
	@Consumes("application/json")
	public void alunoPorUsuario(Usuario usuario) {
		Aluno aluno = repository.alunoPorUsuario(usuario);
		serializer(aluno).exclude("pessoa.usuario.senha").serialize();
	}
	
	@Override
	protected String[] excludeProps() {
		return new String[]{
				"pessoa.usuario.senha",
		};
	}
}