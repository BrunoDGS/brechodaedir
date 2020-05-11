package com.brecho.edir.api.dto.produtos.auxi;

public class ProdutosAux {
	
	private String nome;
	private String cor;
	private String categoria;
	private String tipo;
			
	public ProdutosAux(String nome, String cor, String categoria, String tipo) {
		super();
		this.nome = nome;
		this.cor = cor;
		this.categoria = categoria;
		this.tipo = tipo;
	}
	
	
	public String getNome() {
		return nome;
	}
	public String getCor() {
		return cor;
	}
	public String getCategoria() {
		return categoria;
	}
	public String getTipo() {
		return tipo;
	}

	
}
