package br.com.irdbr.springbootionic.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.irdbr.springbootionic.domain.Categoria;
import br.com.irdbr.springbootionic.repositories.CategoriaRepository;
import br.com.irdbr.springbootionic.services.exceptions.DataIntegrityException;
import br.com.irdbr.springbootionic.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Long id) {
		Optional<Categoria> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId()); // verifica se o objeto existe antes de atualizar
		return repo.save(obj);
	}

	public void delete(Long id) {
		find(id); // verifica se o objeto existe antes de atualizar
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Categoria que possui produtos");
		}
	}
	
}
