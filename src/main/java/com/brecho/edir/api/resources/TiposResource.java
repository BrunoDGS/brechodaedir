package com.brecho.edir.api.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brecho.edir.api.event.RecursoCriadoEvent;
import com.brecho.edir.api.model.TipoModel;
import com.brecho.edir.api.repository.TipoRepository;

@RestController
@RequestMapping("/produtos/tipos")
public class TiposResource {
	
	@Autowired
	private TipoRepository tipos;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional
	@PostMapping
	public ResponseEntity<TipoModel> salvar(@Valid @RequestBody TipoModel tipo, HttpServletResponse response) {
		TipoModel tipoSalvo = tipos.save(tipo);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, tipoSalvo.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(tipoSalvo);

	}
	
	@GetMapping
	public List<TipoModel> listarTodos(){
		
		return tipos.findAll();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover (@PathVariable int id)
	{
		tipos.deleteById(id);
	}


}
