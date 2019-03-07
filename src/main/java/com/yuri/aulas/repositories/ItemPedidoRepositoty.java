package com.yuri.aulas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yuri.aulas.domain.ItemPedido;

@Repository
public interface ItemPedidoRepositoty extends JpaRepository<ItemPedido, Integer>{
  
}  
