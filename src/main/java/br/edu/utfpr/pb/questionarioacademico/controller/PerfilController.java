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
import br.edu.utfpr.pb.questionarioacademico.model.Perfil;
import br.edu.utfpr.pb.questionarioacademico.repository.PerfilRepository;

@SuppressWarnings("serial")
@br.com.caelum.vraptor.Controller
@Path("perfis")
public class PerfilController extends Controller{

	private Result result;
	private PerfilRepository repository;
	
	@Inject
	public PerfilController(Result result,
			PerfilRepository repository) {
		super(result);
		this.result = result;
		this.repository = repository;
	}
	
	/*CDI construtor	*/
	protected PerfilController() {
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
	public void insert(Perfil perfil) {
		repository.insert(perfil);
		result.nothing();
	}
	
	@Put
	@Path("/{perfil.id}")
	@Consumes("application/json")
	public void update(Perfil perfil) {
		repository.update(perfil);
		result.nothing();
	}

	@Delete
	@Path("/{perfil.id}")
	public void delete(Perfil perfil) {
		repository.delete(perfil);
		result.nothing();
	}
}