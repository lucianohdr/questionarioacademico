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
import br.edu.utfpr.pb.questionarioacademico.model.Departamento;
import br.edu.utfpr.pb.questionarioacademico.repository.DepartamentoRepository;

@SuppressWarnings("serial")
@Controller
@Path("departamentos")
public class DepartamentoController extends br.edu.utfpr.pb.questionarioacademico.controller.commons.Controller{

	private Result result;
	private DepartamentoRepository repository;

	@Inject
	public DepartamentoController(Result result, DepartamentoRepository repository) {
		super(result);
		this.repository = repository;
		this.result = result;
	}
	/*CDI only*/
	public DepartamentoController(){
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
	public void insert(Departamento departamento) {
		repository.insert(departamento);
		result.nothing();
	}
	
	@Put
	@Path("/{departamento.id}")
	@Consumes("application/json")
	public void update(Departamento departamento) {
		repository.update(departamento);
		result.nothing();
	}

	@Delete
	@Path("/{departamento.id}")
	public void delete(Departamento departamento) {
		repository.delete(departamento);
		result.nothing();
	}
}
