package com.brecho.edir.api.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brecho.edir.api.event.RecursoCriadoEvent;
import com.brecho.edir.api.model.CategoriasModel;
import com.brecho.edir.api.repository.CategoriasRepository;

@RestController
@RequestMapping("/produtos/categorias")
public class CategoriasResource {
	
	@Autowired
	private CategoriasRepository categorias;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional
	@PostMapping
	public ResponseEntity<CategoriasModel> salvar(@Valid @RequestBody CategoriasModel categoria,
			HttpServletResponse response) {
		CategoriasModel categoriaSalva = categorias.save(categoria);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}
	
	@GetMapping
	public List<CategoriasModel> listarCategoria() {

		return categorias.findAll();

	}


}
