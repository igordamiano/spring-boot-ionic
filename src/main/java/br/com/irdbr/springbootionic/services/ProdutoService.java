package br.com.irdbr.springbootionic.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.irdbr.springbootionic.domain.Produto;
import br.com.irdbr.springbootionic.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	public Produto buscar(Long id) {
		Optional<Produto> obj = repo.findById(id);
		
		return obj.orElse(null);
	}
	
}
