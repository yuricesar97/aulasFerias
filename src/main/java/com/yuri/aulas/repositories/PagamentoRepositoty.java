package com.yuri.aulas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yuri.aulas.domain.Pagamento;

@Repository
public interface PagamentoRepositoty extends JpaRepository<Pagamento, Integer>{
  
}  
