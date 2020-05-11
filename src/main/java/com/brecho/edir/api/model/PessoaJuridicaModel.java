package com.brecho.edir.api.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

@Entity
@Table(name = "juridicasub")
@PrimaryKeyJoinColumn(name = "idcliente")
public class PessoaJuridicaModel extends ClienteModel {
	
	@NotNull
	@Size(max = 14)
	private String cnpj;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	
}
