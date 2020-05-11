package com.brecho.edir.api.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

@Entity
@Table(name = "fisicasub")
@PrimaryKeyJoinColumn(name = "idcliente")
public class PessoaFisicaModel extends ClienteModel {
	
	@NotNull
	@Size(max = 11)
	private String cpf;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
	
}
