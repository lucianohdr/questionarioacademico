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
import br.edu.utfpr.pb.questionarioacademico.model.Contato;
import br.edu.utfpr.pb.questionarioacademico.repository.ContatoRepository;
import br.edu.utfpr.pb.questionarioacademico.seguranca.SecRole;

/**
 * 
 * @author trgp
 *
 * Controller da entidade Contato para crud utilizando restful
 * 
 * Perceba que a anotação @Consumes é utilizada pois a camada da view envia o objeto em formato json e o vraptor se encarrega de transformar em objeto
 *
 */
@Controller
@Path("contatos")
/*@SecRole(roles={"ROLE_ADMIN"}) //Configuração de permissão para a role de admin*/
public class ContatoController extends br.edu.utfpr.pb.questionarioacademico.controller.commons.Controller {


	Result result;
	ContatoRepository repository;

	protected ContatoController() {
		this(null, null);
	}
	
	@Inject
	public ContatoController(Result result, ContatoRepository repository) {
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
	public void insert(Contato contato) {
		repository.insert(contato);
		result.nothing();
	}

	@Put
	@Path("/{contato.id}")
	@Consumes("application/json")
	public void update(Contato contato) {
		repository.update(contato);
		result.nothing();
	}

	@Delete
	@Path("/{contato.id}")
	public void delete(Contato contato) {
		repository.delete(contato);
		result.nothing();
		
	}
}