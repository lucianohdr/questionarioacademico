package br.edu.utfpr.pb.questionarioacademico.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="questionariodisponivel")
public class Questionariodisponivel extends br.edu.utfpr.pb.questionarioacademico.model.commons.Entity{

	@OneToOne
	private Disciplina disciplina;
	
	@ManyToOne
	private Questionario questionario;
	
	@OneToMany(mappedBy="questionariodisponivel", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Questionarioresposta> questionariorespostas;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="usuariosrespondido",
			joinColumns=@JoinColumn(name="idquestionariodisponivel"),
			inverseJoinColumns=@JoinColumn(name="idusuario"))
	private Set<Usuario> usuariosRespondidos;
	
	public void addUsuario(Usuario usuario){
		getUsuariosRespondidos().add(usuario);
	}
	
	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Questionario getQuestionario() {
		return questionario;
	}

	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}
	
	public List<Questionarioresposta> getQuestionariorespostas() {
		return questionariorespostas;
	}

	public void setQuestionariorespostas(
			List<Questionarioresposta> questionariorespostas) {
		this.questionariorespostas = questionariorespostas;
	}

	public Set<Usuario> getUsuariosRespondidos() {
		return usuariosRespondidos;
	}

	public void setUsuariosRespondidos(Set<Usuario> usuariosRespondidos) {
		this.usuariosRespondidos = usuariosRespondidos;
	}
}