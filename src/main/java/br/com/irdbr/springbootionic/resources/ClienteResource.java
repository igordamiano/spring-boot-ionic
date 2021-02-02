package br.com.irdbr.springbootionic.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.irdbr.springbootionic.domain.Cliente;
import br.com.irdbr.springbootionic.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value = "/{id}" , method = RequestMethod.GET)
	public ResponseEntity<Cliente> findById(@PathVariable Long id) {
		
		Cliente obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}

}
