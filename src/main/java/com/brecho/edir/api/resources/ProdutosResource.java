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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brecho.edir.api.event.RecursoCriadoEvent;
import com.brecho.edir.api.model.CategoriasModel;
import com.brecho.edir.api.model.ProdutosModel;
import com.brecho.edir.api.model.TipoModel;
import com.brecho.edir.api.repository.CategoriasRepository;
import com.brecho.edir.api.repository.ProdutosRepository;
import com.brecho.edir.api.repository.TipoRepository;

@RestController
public class ProdutosResource {
	
	@Autowired
	private ProdutosRepository produtos;
	
	@Autowired
	private TipoRepository tipos;
	
	@Autowired
	private CategoriasRepository categorias;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional
	@PostMapping("/produtos")
	public ResponseEntity<ProdutosModel> salvar(@Valid @RequestBody ProdutosModel produto, HttpServletResponse response) {
			ProdutosModel produtoSalvo = produtos.save(produto);
			
			publisher.publishEvent(new RecursoCriadoEvent(this, response, produtoSalvo.getId()));
			
			return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
		
	}	

	@Transactional
	@PostMapping("/produtos/tipos")
	public ResponseEntity<TipoModel> salvar(@Valid @RequestBody TipoModel tipo, HttpServletResponse response) {
			TipoModel tipoSalvo = tipos.save(tipo);
			
			publisher.publishEvent(new RecursoCriadoEvent(this, response, tipoSalvo.getId()));
			
			return ResponseEntity.status(HttpStatus.CREATED).body(tipoSalvo);
		
	}
	
	@Transactional
	@PostMapping("/produtos/categorias")
	public ResponseEntity<CategoriasModel> salvar(@Valid @RequestBody CategoriasModel categoria, HttpServletResponse response) {
		CategoriasModel categoriaSalva = categorias.save(categoria);
			
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}
		
	@GetMapping("/produtos/{id}")
	public Optional<ProdutosModel> buscarPeloId(@PathVariable Integer id) {
		return produtos.findById(id);
	}
	
	
	@GetMapping("/produtos")
	public List<ProdutosModel> listar(){
		
		return produtos.findAll();
		
	}
	
	@GetMapping("/produtos/categorias")
	public List<CategoriasModel> listarCategoria(){
		
		return categorias.findAll();
		
	}

}
