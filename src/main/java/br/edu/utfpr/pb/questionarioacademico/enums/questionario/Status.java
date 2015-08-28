package br.edu.utfpr.pb.questionarioacademico.enums.questionario;

public enum Status {
	SALVO("Salvo"),
	EMCURSO("Em Curso"),
	FINALIZADO("Finalizado");

	private Status(String descricao){
		this.descricao = descricao;
	}
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return this.descricao;
	}
}