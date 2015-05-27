package br.edu.utfpr.pb.questionarioacademico.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.edu.utfpr.pb.questionarioacademico.controller.commons.Controller;
import br.edu.utfpr.pb.questionarioacademico.model.TipoUsuario;
import br.edu.utfpr.pb.questionarioacademico.repository.TipoUsuarioRepository;
//TODO: resolver problema de nomes compostos. Na view deve aparecer tipousuario e nao tipoUsuario
@br.com.caelum.vraptor.Controller
@Path("tipousuarios")
public class TipoUsuarioController extends Controller{

	private Result result;
	private TipoUsuarioRepository repository;
	
	@Inject
	public TipoUsuarioController(Result result,
			TipoUsuarioRepository repository) {
		super(result);
		this.result = result;
		this.repository = repository;
	}
	
	protected TipoUsuarioController() {
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
	public void insert(TipoUsuario tipousuario) {
		repository.insert(tipousuario);
		result.nothing();
	}
	
	@Put
	@Path("/{tipousuario.id}")
	@Consumes("application/json")
	public void update(TipoUsuario tipousuario) {
		repository.update(tipousuario);
		result.nothing();
	}

	@Delete
	@Path("/{tipousuario.id}")
	public void delete(TipoUsuario tipousuario) {
		repository.delete(tipousuario);
		result.nothing();
	}
}