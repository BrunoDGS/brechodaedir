package com.brecho.edir.api.resources;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brecho.edir.api.event.RecursoCriadoEvent;
import com.brecho.edir.api.model.ProdutosModel;
import com.brecho.edir.api.repository.ProdutosRepository;
import com.brecho.edir.api.service.ProdutosService;

@RestController
@RequestMapping("/produtos")
public class ProdutosResource {

	@Autowired
	private ProdutosRepository produtosRepository;

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private ProdutosService produtosService;

	@Transactional
	@PostMapping
	public ResponseEntity<ProdutosModel> salvar(@Valid @RequestBody ProdutosModel produto,
			HttpServletResponse response) {
		ProdutosModel produtoSalvo = produtosRepository.save(produto);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, produtoSalvo.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);

	}

	@Transactional
	@GetMapping
	public List<ProdutosModel> listar() {

		return produtosRepository.findAll();

	}

	@Transactional
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPeloId(@PathVariable Integer id) {

		Optional<ProdutosModel> produtoRecuperado = produtosRepository.findById(id);

		return produtoRecuperado.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(produtoRecuperado)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<ProdutosModel> atualizar(@PathVariable Integer id, @Valid @RequestBody ProdutosModel produto){
		
		ProdutosModel produtoRecuperado = produtosService.salvar(id, produto);		
		return ResponseEntity.ok(produtoRecuperado);
		
	}
}