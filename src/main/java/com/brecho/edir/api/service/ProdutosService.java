package com.brecho.edir.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.brecho.edir.api.model.ProdutosModel;
import com.brecho.edir.api.repository.ProdutosRepository;

@Service
public class ProdutosService {
	
	@Autowired
	private ProdutosRepository produtosRepository;
	
	public ProdutosModel salvar (Integer id, ProdutosModel produtos) {
		
		ProdutosModel produtoRecuperado = produtosRepository.findById(id);
		

		if(produtoRecuperado == null) {
			
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(produtos, produtoRecuperado, "id");
		return produtosRepository.save(produtoRecuperado);
	}

}
