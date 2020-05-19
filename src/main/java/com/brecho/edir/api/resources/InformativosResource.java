package com.brecho.edir.api.resources;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brecho.edir.api.model.InformativosModel;
import com.brecho.edir.api.repository.InformativosRepository;

@RestController
public class InformativosResource {
	
	@Autowired
	private InformativosRepository informativosRepository;
	
	@Transactional
	@PostMapping("/informativos")
	@ResponseStatus(HttpStatus.CREATED)
	public void salvar(@RequestBody InformativosModel informativos) {
		
		informativosRepository.save(informativos);
	}

}
