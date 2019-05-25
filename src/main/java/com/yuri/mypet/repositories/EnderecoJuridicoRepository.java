package com.yuri.mypet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yuri.mypet.domain.EnderecoJuridico;

@Repository
public interface EnderecoJuridicoRepository extends JpaRepository<EnderecoJuridico, Integer>{
  
}  
