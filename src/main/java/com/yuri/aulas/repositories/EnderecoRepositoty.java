package com.yuri.aulas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yuri.aulas.domain.Endereco;

@Repository
public interface EnderecoRepositoty extends JpaRepository<Endereco, Integer>{
  
}  
