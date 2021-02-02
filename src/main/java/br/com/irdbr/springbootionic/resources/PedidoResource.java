package br.com.irdbr.springbootionic.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.irdbr.springbootionic.domain.Pedido;
import br.com.irdbr.springbootionic.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService service;
	
	@RequestMapping(value = "/{id}" , method = RequestMethod.GET)
	public ResponseEntity<Pedido> findById(@PathVariable Long id) {
		
		Pedido obj = service.buscar(id);
		
		return ResponseEntity.ok().body(obj);
	}

}
