package br.com.irdbr.springbootionic.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.irdbr.springbootionic.domain.Categoria;
import br.com.irdbr.springbootionic.domain.Cidade;
import br.com.irdbr.springbootionic.domain.Cliente;
import br.com.irdbr.springbootionic.domain.Endereco;
import br.com.irdbr.springbootionic.domain.Estado;
import br.com.irdbr.springbootionic.domain.ItemPedido;
import br.com.irdbr.springbootionic.domain.Pagamento;
import br.com.irdbr.springbootionic.domain.PagamentoComBoleto;
import br.com.irdbr.springbootionic.domain.PagamentoComCartao;
import br.com.irdbr.springbootionic.domain.Pedido;
import br.com.irdbr.springbootionic.domain.Produto;
import br.com.irdbr.springbootionic.domain.enums.EstadoPagamento;
import br.com.irdbr.springbootionic.domain.enums.TipoCliente;
import br.com.irdbr.springbootionic.repositories.CategoriaRepository;
import br.com.irdbr.springbootionic.repositories.CidadeRepository;
import br.com.irdbr.springbootionic.repositories.ClienteRepository;
import br.com.irdbr.springbootionic.repositories.EnderecoRepository;
import br.com.irdbr.springbootionic.repositories.EstadoRepository;
import br.com.irdbr.springbootionic.repositories.ItemPedidoRepository;
import br.com.irdbr.springbootionic.repositories.PagamentoRepository;
import br.com.irdbr.springbootionic.repositories.PedidoRepository;
import br.com.irdbr.springbootionic.repositories.ProdutoRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	
	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null, "Uberlândia", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2, cid3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));

		Cliente cli1 = new Cliente(null, "Maria da Silva", "maria@hotmail.com", "38890493825", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("1198776489", "1189478933"));
		
		Endereco end1 = new Endereco(null, "Rua Espinhos", "199", "Apto 2 Bl-2", "Rebouças", "09976890", cli1, cid1);
		Endereco end2 = new Endereco(null, "Estrada Guarapiranga", "3499", "Andar 1", "Vila Andrade", "09976777", cli1, cid2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("10/01/2021 19:30"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("18/01/2021 21:31"), cli1, end2);
		
		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);
		
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("19/01/2021 00:00"), null);
		ped2.setPagamento(pag2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));
		
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}

}
