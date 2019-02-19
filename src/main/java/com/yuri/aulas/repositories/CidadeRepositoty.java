package com.yuri.aulas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yuri.aulas.domain.Cidade;

@Repository
public interface CidadeRepositoty extends JpaRepository<Cidade, Integer>{
  
}  
