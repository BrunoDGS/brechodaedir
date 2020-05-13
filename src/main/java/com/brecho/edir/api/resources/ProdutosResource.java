package com.brecho.edir.api.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@Transactional
	@PostMapping("/produtos")
	public ResponseEntity<ProdutosModel> salvar(@Valid @RequestBody ProdutosModel produto, HttpServletResponse response) {
			ProdutosModel produtoSalvo = produtos.save(produto);
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
					.buildAndExpand(produtoSalvo.getId()).toUri();
			
			response.addHeader("Location", uri.toASCIIString());
			
			return ResponseEntity.created(uri).body(produtoSalvo);
		
	}	

	@Transactional
	@PostMapping("/produtos/tipos")
	public ResponseEntity<TipoModel> salvar(@Valid @RequestBody TipoModel tipo, HttpServletResponse response) {
			TipoModel tipoSalvo = tipos.save(tipo);
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
					.buildAndExpand(tipoSalvo.getId()).toUri();
			
			response.addHeader("Location", uri.toASCIIString());
			
			return ResponseEntity.created(uri).body(tipoSalvo);
		
	}
	
	@Transactional
	@PostMapping("/produtos/categorias")
	public ResponseEntity<CategoriasModel> salvar(@Valid @RequestBody CategoriasModel categoria, HttpServletResponse response) {
		CategoriasModel categoriaSalvo = categorias.save(categoria);
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
					.buildAndExpand(categoriaSalvo.getId()).toUri();
			
			response.addHeader("Location", uri.toASCIIString());
		
			return ResponseEntity.created(uri).body(categoriaSalvo);
	}
		
	@GetMapping("/produtos/{id}")
	public Optional<ProdutosModel> buscarPeloId(@PathVariable Integer id) {
		return produtos.findById(id);
	}
	
	
	@GetMapping("/produtos")
	public List<ProdutosModel> listar(){
		
		return produtos.findAll();
		
	}

}
