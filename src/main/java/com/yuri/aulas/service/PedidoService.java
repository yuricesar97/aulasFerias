package com.yuri.aulas.service;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.yuri.aulas.domain.Pedido;
import com.yuri.aulas.repositories.PedidoRepositoty;
import com.yuri.aulas.service.exceptions.ObjectNotFoundException;


@Service
public class PedidoService {

	@Autowired
	PedidoRepositoty repo;
	
	public Pedido find(Integer id) {
		
		Optional<Pedido> op  = repo.findById(id);
		
		return op.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id +", tipo: " + Pedido.class.getName()));
	}
	
}


