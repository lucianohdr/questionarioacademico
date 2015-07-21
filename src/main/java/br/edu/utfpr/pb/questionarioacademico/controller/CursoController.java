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
import br.edu.utfpr.pb.questionarioacademico.model.Curso;
import br.edu.utfpr.pb.questionarioacademico.repository.CursoRepository;

@SuppressWarnings("serial")
@Controller
@Path("cursos")
public class CursoController extends br.edu.utfpr.pb.questionarioacademico.controller.commons.Controller{

	private Result result;
	private CursoRepository repository;
	
	@Inject
	public CursoController(Result result, CursoRepository repository) {
		super(result);
		this.result = result;
		this.repository = repository;
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
	public void insert(Curso curso) {
		repository.insert(curso);
		result.nothing();
	}
	
	@Put
	@Path("/{curso.id}")
	@Consumes("application/json")
	public void update(Curso curso) {
		repository.update(curso);
		result.nothing();
	}

	@Delete
	@Path("/{curso.id}")
	public void delete(Curso curso) {
		repository.delete(curso);
		result.nothing();
	}
}