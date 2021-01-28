package br.com.irdbr.springbootionic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.irdbr.springbootionic.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{
	
}
