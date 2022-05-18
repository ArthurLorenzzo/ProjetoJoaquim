package br.mackenzie.ps2.api.entity;

import javax.persistence.*;

@Entity
@Table(name = "filmes")
public class Filme {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	long id;

	@Column(nullable = false)
	String nome;

	@Column(nullable = false)
	int lancamento;

	@Column(nullable = false)
	String categoria;

	@Column(nullable = false)
	String descri;

	@Column(nullable = false)
	double nota;


	public Filme() {}
	
	public Filme(String nome, int lancamento, String categoria, String descri, double nota) {
		this.nome = nome;
		this.lancamento = lancamento;
		this.categoria = categoria;
		this.descri = descri;
		this.nota = nota;
	}

	public long getId() {return id;	}
	public void setId(long id) {this.id = id;}
	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
	public int getLancamento() {return lancamento;}
	public void setLancamento(int lancamento) {this.lancamento = lancamento;}
	public String getCategoria() {return categoria;}
	public void setCategoria(String categoria) {this.categoria = categoria;}
	public String getDescri(){return descri;}
	public void setDescri(String descri){this.descri=descri;}
	public double getNota() {return nota;}
	public void setNota(double nota) {this.nota = nota;}
	

}
