package com.yuri.aulas.service;


import java.net.URI;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.yuri.aulas.domain.ItemPedido;
import com.yuri.aulas.domain.PagamentoComBoleto;
import com.yuri.aulas.domain.Pedido;
import com.yuri.aulas.domain.enums.EstadoPagamento;
import com.yuri.aulas.repositories.ItemPedidoRepositoty;
import com.yuri.aulas.repositories.PagamentoRepositoty;
import com.yuri.aulas.repositories.PedidoRepositoty;
import com.yuri.aulas.repositories.produtoRepositoty;
import com.yuri.aulas.service.exceptions.ObjectNotFoundException;


@Service
public class PedidoService {

	@Autowired
	PedidoRepositoty repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepositoty pagamentoRepositoty;
	
	@Autowired
	private produtoRepositoty produtoRepositoty;
	
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private ItemPedidoRepositoty itemPedidoRepositoty;
	
	public Pedido find(Integer id) {
		
		Optional<Pedido> op  = repo.findById(id);
		
		return op.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id +", tipo: " + Pedido.class.getName()));
	}
	

	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) { // se o meu pagamento for do tipo pagamento com boleto gera uma data para ele
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
			
		}
		
		repo.save(obj);
		pagamentoRepositoty.save(obj.getPagamento());
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
	
		itemPedidoRepositoty.saveAll(obj.getItens());
		return obj;
	}
}


