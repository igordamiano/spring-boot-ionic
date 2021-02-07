package br.com.irdbr.springbootionic.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.irdbr.springbootionic.domain.ItemPedido;
import br.com.irdbr.springbootionic.domain.PagamentoComBoleto;
import br.com.irdbr.springbootionic.domain.Pedido;
import br.com.irdbr.springbootionic.domain.Produto;
import br.com.irdbr.springbootionic.domain.enums.EstadoPagamento;
import br.com.irdbr.springbootionic.repositories.ItemPedidoRepository;
import br.com.irdbr.springbootionic.repositories.PagamentoRepository;
import br.com.irdbr.springbootionic.repositories.PedidoRepository;
import br.com.irdbr.springbootionic.repositories.ProdutoRepository;
import br.com.irdbr.springbootionic.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido buscar(Long id) {
		Optional<Pedido> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		
	}
	
	//@Transactional(readOnly = true)
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pgto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pgto, obj.getInstante());
		}
		
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		
		for(ItemPedido ip: obj.getItens()) {
			ip.setDesconto(0.0);
			Optional<Produto> prd = produtoRepository.findById(ip.getProduto().getId());
			ip.setProduto(prd.orElse(null));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}
	
}
