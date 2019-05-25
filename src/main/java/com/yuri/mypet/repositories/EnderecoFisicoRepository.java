package com.yuri.mypet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yuri.mypet.domain.EnderecoFisico;

@Repository
public interface EnderecoFisicoRepository extends JpaRepository<EnderecoFisico, Integer>{
  
}  
