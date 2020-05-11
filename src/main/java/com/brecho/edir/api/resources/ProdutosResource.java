package com.brecho.edir.api.resources;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.brecho.edir.api.model.ProdutosModel;
import com.brecho.edir.api.repository.ProdutosRepository;

@RestController
public class ProdutosResource {
	
	@Autowired
	private ProdutosRepository produtos;
	
	@Transactional
	@PostMapping("/produtos")
	@ResponseStatus(HttpStatus.CREATED)
	public void salvar(@RequestBody ProdutosModel produto, HttpServletResponse response) {
		try {
			ProdutosModel produtoSalvo = produtos.save(produto);
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
					.buildAndExpand(produtoSalvo.getId()).toUri();
			
			response.addHeader("Location", uri.toASCIIString());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		
	@GetMapping("/produtos")
	public List<ProdutosModel> listar(){
		
		return produtos.findAll();
		
	}

}
