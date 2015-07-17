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
import br.edu.utfpr.pb.questionarioacademico.model.Categoriaquestionario;
import br.edu.utfpr.pb.questionarioacademico.repository.CategoriaQuestionarioRepository;

@SuppressWarnings("serial")
@Controller
@Path("categoriaquestionarios")
public class CategoriaQuestionarioController 
	extends br.edu.utfpr.pb.questionarioacademico.controller.commons.Controller{
	
	private Result result;
	private CategoriaQuestionarioRepository repository;
	
	@Inject
	public CategoriaQuestionarioController(Result result, CategoriaQuestionarioRepository repository) {
		super(result);
		this.result = result;
		this.repository = repository;
	}

	protected CategoriaQuestionarioController (){
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
	public void insert(Categoriaquestionario categoriaquestionario) {
		repository.insert(categoriaquestionario);
		result.nothing();
	}
	
	@Put
	@Path("/{categoriaquestionario.id}")
	@Consumes("application/json")
	public void update(Categoriaquestionario categoriaquestionario) {
		repository.update(categoriaquestionario);
		result.nothing();
	}

	@Delete
	@Path("/{categoriaquestionario.id}")
	public void delete(Categoriaquestionario categoriaquestionario) {
		repository.delete(categoriaquestionario);
		result.nothing();
	}
}
