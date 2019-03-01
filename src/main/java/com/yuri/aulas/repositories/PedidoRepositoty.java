package com.yuri.aulas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yuri.aulas.domain.Pedido;

@Repository
public interface PedidoRepositoty extends JpaRepository<Pedido, Integer>{
  
}  
