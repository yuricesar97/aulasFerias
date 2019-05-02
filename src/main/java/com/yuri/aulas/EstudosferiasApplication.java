package com.yuri.aulas;


import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.yuri.aulas.domain.Categoria;
import com.yuri.aulas.domain.Cidade;
import com.yuri.aulas.domain.Cliente;
import com.yuri.aulas.domain.Endereco;
import com.yuri.aulas.domain.Estado;
import com.yuri.aulas.domain.ItemPedido;
import com.yuri.aulas.domain.Pagamento;
import com.yuri.aulas.domain.PagamentoComBoleto;
import com.yuri.aulas.domain.PagamentoComCartão;
import com.yuri.aulas.domain.Pedido;
import com.yuri.aulas.domain.Produto;
import com.yuri.aulas.domain.enums.EstadoPagamento;
import com.yuri.aulas.domain.enums.TipoCliente;
import com.yuri.aulas.repositories.CategoriasRepository;
import com.yuri.aulas.repositories.CidadeRepositoty;
import com.yuri.aulas.repositories.ClienteRepositoty;
import com.yuri.aulas.repositories.EnderecoRepositoty;
import com.yuri.aulas.repositories.EstadoRepositoty;
import com.yuri.aulas.repositories.ItemPedidoRepositoty;
import com.yuri.aulas.repositories.PagamentoRepositoty;
import com.yuri.aulas.repositories.PedidoRepositoty;
import com.yuri.aulas.repositories.produtoRepositoty;



@SpringBootApplication
public class EstudosferiasApplication implements CommandLineRunner {

	@Autowired
	private CategoriasRepository categoriaRepository;
	@Autowired
	private produtoRepositoty produtoRepository;
	@Autowired
	private CidadeRepositoty cidadeRepositoty;
	@Autowired
	private EstadoRepositoty  estadoRepositoty;
	@Autowired
	private ClienteRepositoty clienteRepositoty;
	@Autowired
	private EnderecoRepositoty  enderecoRepositoty;
	@Autowired
	private PedidoRepositoty pedidoRepositoty;
	@Autowired
	private PagamentoRepositoty pagamentoRepositoty;
	@Autowired
	private ItemPedidoRepositoty itemPedidoRepositoty;
	

	public static void main(String[] args)  {
		SpringApplication.run(EstudosferiasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônico");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Deceroção");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		
		
		
		Produto produto1 = new Produto(null, "Computador", 2000.00);
		Produto produto2 = new Produto(null, "Impressora", 800.00);
		Produto produto3 = new Produto(null, "Mouse", 80.00);
		Produto produto4 = new Produto(null, "Mesa de escritório", 300.00);
		Produto produto5 = new Produto(null, "Toalha", 50.00);
		Produto produto6 = new Produto(null, "Colcha", 200.00);
		Produto produto7 = new Produto(null, "TV true color", 1200.00);
		Produto produto8 = new Produto(null, "Roçadeira", 800.00);
		Produto produto9 = new Produto(null, "Abajour", 100.00);
		Produto produto10 = new Produto(null, "Pendente", 180.00);
		Produto produto11 = new Produto(null, "Shampoo", 90.00);
		
		
		
		
		
		cat1.getProdutos().addAll(Arrays.asList(produto1,produto2,produto3));
		cat2.getProdutos().addAll(Arrays.asList(produto2,produto4));
		cat3.getProdutos().addAll(Arrays.asList(produto5,produto6));
		cat4.getProdutos().addAll(Arrays.asList(produto1,produto2,produto3,produto7));
		cat5.getProdutos().addAll(Arrays.asList(produto8));
		cat6.getProdutos().addAll(Arrays.asList(produto9,produto10));
		cat7.getProdutos().addAll(Arrays.asList(produto11));
		
		produto1.getCategorias().addAll(Arrays.asList(cat1,cat4));
		produto2.getCategorias().addAll(Arrays.asList(cat1,cat2,cat4));
		produto3.getCategorias().addAll(Arrays.asList(cat1,cat4));
		produto4.getCategorias().addAll(Arrays.asList(cat2));
		produto5.getCategorias().addAll(Arrays.asList(cat3));
		produto6.getCategorias().addAll(Arrays.asList(cat3));
		produto7.getCategorias().addAll(Arrays.asList(cat4));
		produto8.getCategorias().addAll(Arrays.asList(cat5));
		produto9.getCategorias().addAll(Arrays.asList(cat6));
		produto10.getCategorias().addAll(Arrays.asList(cat6));
		produto11.getCategorias().addAll(Arrays.asList(cat7));
		
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
		produtoRepository.saveAll(Arrays.asList(produto1,produto2,produto3,produto4,produto5,produto6,produto7,produto8,produto9,produto10,produto11));
		
	
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null, "Uberlândia",est1);
		Cidade cid2 = new Cidade(null, "São Paulo",est2);
		Cidade cid3 = new Cidade(null, "Campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2,cid3));
		
		
		estadoRepositoty.saveAll(Arrays.asList(est1,est2));
		cidadeRepositoty.saveAll(Arrays.asList(cid1,cid2,cid3));
	
		Cliente cli1 = new Cliente (null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICO);
		
       cli1.getTelefones().addAll(Arrays.asList("27363323", "938393"));
       
       Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cid1, cli1 );
       Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cid2, cli1);
	  
       cli1.getEndereço().addAll(Arrays.asList(e1,e2)); //cliente conhecendo seu endereços       
       
	   clienteRepositoty.saveAll(Arrays.asList(cli1));
	   enderecoRepositoty.saveAll(Arrays.asList(e1,e2));
       
	
	   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm"); /// para formatação de data e hora
	   
	   Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32 "), cli1, e1);
	   Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35 "), cli1, e2);
	   
	   Pagamento pgto1 = new PagamentoComCartão(null, EstadoPagamento.QUITADO, ped1, 6);
	   ped1.setPagamento(pgto1);
	  
	   
	    Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
	   	ped2.setPagamento(pgto2);
	   	
	   	cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
	   	
	  
	     pedidoRepositoty.saveAll(Arrays.asList(ped1,ped2));
	     pagamentoRepositoty.saveAll(Arrays.asList(pgto1,pgto2));
	    	
	   	
	     ItemPedido ip1 = new ItemPedido(ped1, produto1, 0.00, 1, 2000.00);
	     ItemPedido ip2 = new ItemPedido(ped1, produto3, 0.00, 2, 80.00);
	     ItemPedido ip3 = new ItemPedido(ped2, produto2, 100.00, 1, 800.00);
	     
	     ped1.getItens().addAll(Arrays.asList(ip1,ip2));
	     ped2.getItens().addAll(Arrays.asList(ip3));
	     
	     produto1.getItens().addAll(Arrays.asList(ip1));
	     produto2.getItens().addAll(Arrays.asList(ip3));
	     produto3.getItens().addAll(Arrays.asList(ip2));
	     
	     itemPedidoRepositoty.saveAll(Arrays.asList(ip1,ip2,ip3));
	     
	}

}

