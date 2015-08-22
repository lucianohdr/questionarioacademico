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
import br.com.caelum.vraptor.view.Results;
import br.edu.utfpr.pb.questionarioacademico.model.Usuario;
import br.edu.utfpr.pb.questionarioacademico.repository.UsuarioRepository;
import br.edu.utfpr.pb.questionarioacademico.seguranca.Hasher;

@SuppressWarnings("serial")
@Controller
@Path("usuarios")
public class UsuarioController extends br.edu.utfpr.pb.questionarioacademico.controller.commons.Controller{
	
	private Result result;
	private UsuarioRepository repository;

	@Inject
	public UsuarioController(Result result, UsuarioRepository repository) {
		super(result);
		this.repository = repository;
		this.result = result;
	}
	
	protected UsuarioController() {
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
	public void insert(Usuario usuario) {
		repository.insert(usuario);
		result.nothing();
	}
	
	@Put
	@Path("/{usuario.id}")
	@Consumes("application/json")
	public void update(Usuario usuario) {
		repository.update(usuario);
		result.nothing();
	}

	@Delete
	@Path("/{usuario.id}")
	public void delete(Usuario usuario) {
		repository.delete(usuario);
		result.nothing();
	}
	
	@Get
	@Path("/loginDisponivel/")
	public void loginDisponivel(String login) {
		
		boolean disponivel = repository.loginDisponivel(login);
		
		if(disponivel){
			result.use(Results.status()).ok();
		} else {
			result.use(Results.status()).forbidden("Usuario já existe");
		}
	}
}