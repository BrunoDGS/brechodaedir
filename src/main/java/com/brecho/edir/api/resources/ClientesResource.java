package com.brecho.edir.api.resources;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brecho.edir.api.model.PessoaFisicaModel;
import com.brecho.edir.api.model.PessoaJuridicaModel;
import com.brecho.edir.api.repository.ClientesRepository;

@RestController
public class ClientesResource {
	
	@Autowired
	ClientesRepository repository;
	
	@Transactional
	@PostMapping("/clientes/fisica")
	@ResponseStatus(HttpStatus.CREATED)
	public void salvar(@RequestBody PessoaFisicaModel fisica) {
		repository.save(fisica);
	}
	
	@Transactional
	@PostMapping("/clientes/juridica")
	@ResponseStatus(HttpStatus.CREATED)
	public void salvar(@RequestBody PessoaJuridicaModel juridica) {
		repository.save(juridica);
	}
	
	
	
	

}
