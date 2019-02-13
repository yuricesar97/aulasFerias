package com.yuri.aulas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yuri.aulas.domain.Produto;

@Repository
public interface ProdutoRepositoty extends JpaRepository<Produto, Integer>{
  
}  
