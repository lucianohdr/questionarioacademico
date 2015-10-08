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
import br.edu.utfpr.pb.questionarioacademico.model.Perfil;
import br.edu.utfpr.pb.questionarioacademico.model.Professor;
import br.edu.utfpr.pb.questionarioacademico.model.Usuario;
import br.edu.utfpr.pb.questionarioacademico.repository.ProfessorRepository;
import br.edu.utfpr.pb.questionarioacademico.repository.UsuarioRepository;
import br.edu.utfpr.pb.questionarioacademico.seguranca.regras.LoggedAccessRule;

@SuppressWarnings("serial")
@Controller
@Path("professors")
@CustomBrutauthRules(LoggedAccessRule.class)
public class ProfessorController extends br.edu.utfpr.pb.questionarioacademico.controller.commons.Controller{

	private Result result;
	private ProfessorRepository repository;
	private UsuarioRepository usuarioRepository;
	
	@Inject
	public ProfessorController(Result result, 
							   ProfessorRepository repository, 
							   UsuarioRepository usuarioRepository) {
		super(result);
		this.result = result;
		this.repository = repository;
		this.usuarioRepository = usuarioRepository;
	}
	
	/*CDI construtor*/
	protected ProfessorController(){
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
	public void insert(Professor professor) {
		
		//setando o perfil professor em usuario
		professor.getPessoa().getUsuario().getPerfis().add(new Perfil("PROFESSOR"));
		
		professor.getPessoa()
		.setUsuario(
				usuarioRepository.insertReturn(professor.getPessoa().getUsuario())
				);
		
		repository.insertReturn(professor);
		result.nothing();
	}
	
	@Put
	@Path("/{professor.id}")
	@Consumes("application/json")
	public void update(Professor professor) {
		
		Professor professorAux = repository.find(professor.getId());
		//setando novamente a senha ainda presente no banco
		professor.getPessoa().getUsuario().setSenha(professorAux.getPessoa().getUsuario().getSenha());
		
		repository.update(professor);
		result.nothing();
	}

	@Delete
	@Path("/{professor.id}")
	public void delete(Professor professor) {
		repository.delete(professor);
		result.nothing();
	}
	
	@Post
	@Path("/professorPorUsuario")
	@Consumes("application/json")
	public void professorPorUsuario(Usuario usuario) {
		Professor professor = repository.professorPorUsuario(usuario);
		serializer(professor).exclude("pessoa.usuario.senha").serialize();
	}
}